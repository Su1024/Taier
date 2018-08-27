package com.dtstack.sql.main;

import com.google.common.base.Charsets;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SQLContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

public class SqlProxy {

    private static final Logger logger = LoggerFactory.getLogger(SqlProxy.class);

    private static final ObjectMapper objMapper = new ObjectMapper();

    private static final String DEFAULT_APP_NAME = "spark_default_name";

    public void runJob(String submitSql, String appName){

        if(appName == null){
            appName = DEFAULT_APP_NAME;
        }

        SparkConf confs = new SparkConf();
        //confs.set("spark.driver.extraLibraryPath", "hdfs://kudu1:9000/sparkjars/lib");
        //confs.set("spark.yarn.archive", "hdfs://kudu1:9000/sparkjars/lib");
        //confs.set("spark.driver.userClassPathFirst", "hdfs://kudu1:9000/sparkjars/lib");
        SparkContext sparkContext = new SparkContext(confs);
        SQLContext spark = new SQLContext(sparkContext);

        //解压sql
        String unzipSql = SqlProxy.unzip(submitSql);

        //屏蔽引号内的 分号
        List<String> sqlArray = splitIgnoreQuota(unzipSql, ';');
        for(String sql : sqlArray){
            if(sql == null || sql.trim().length() == 0){
                continue;
            }

            spark.sql(sql);
        }
    }

    /**
     * 根据指定分隔符分割字符串---忽略在引号里面的分隔符
     * @param str
     * @param delimiter
     * @return
     */
    public static List<String> splitIgnoreQuota(String str, char delimiter){
        List<String> tokensList = new ArrayList<>();
        boolean inQuotes = false;
        boolean inSingleQuotes = false;
        StringBuilder b = new StringBuilder();
        for (char c : str.toCharArray()) {
            if(c == delimiter){
                if (inQuotes) {
                    b.append(c);
                } else if(inSingleQuotes){
                    b.append(c);
                }else {
                    tokensList.add(b.toString());
                    b = new StringBuilder();
                }
            }else if(c == '\"'){
                inQuotes = !inQuotes;
                b.append(c);
            }else if(c == '\''){
                inSingleQuotes = !inSingleQuotes;
                b.append(c);
            }else{
                b.append(c);
            }
        }

        tokensList.add(b.toString());

        return tokensList;
    }

    /**
     * 使用zip进行解压缩
     * @param  compressedStr 压缩后的文本
     * @return 解压后的字符串
     */
    public static final String unzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        ZipInputStream zin = null;
        String decompressed = null;
        try {
            byte[] compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            out = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(compressed);
            zin = new ZipInputStream(in);
            zin.getNextEntry();
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = zin.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            decompressed = null;
        } finally {
            if (zin != null) {
                try {
                    zin.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return decompressed;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        if(args.length < 1){
            logger.error("must set args for sql job!!!");
            throw new RuntimeException("must set args for sql job!!!");
        }

        SqlProxy sqlProxy = new SqlProxy();
        String argInfo = args[0];
        argInfo = URLDecoder.decode(argInfo, Charsets.UTF_8.name());

        Map<String, Object> argsMap = null;
        try{
            argsMap = objMapper.readValue(argInfo, Map.class);
        }catch (Exception e){
            logger.error("", e);
            throw new RuntimeException("parse args json error, message: " + argInfo, e);
        }

        String sql = (String) argsMap.get("sql");
        String appName = argsMap.get("appName") == null ? null : (String) argsMap.get("appName");
        sqlProxy.runJob(sql, appName);
    }
}
