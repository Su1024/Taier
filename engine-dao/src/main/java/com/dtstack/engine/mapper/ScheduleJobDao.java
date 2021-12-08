/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dtstack.engine.mapper;

import com.dtstack.engine.domain.ScheduleJob;
import com.dtstack.engine.domain.po.SimpleScheduleJobPO;
import com.dtstack.engine.dto.ScheduleJobDTO;
import com.dtstack.engine.dto.StatusCount;
import com.dtstack.engine.pager.PageQuery;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * company: www.dtstack.com
 * author: toutian
 * create: 2019/10/22
 */
public interface ScheduleJobDao {

    ScheduleJob getOne(@Param("id") Long id);

    List<ScheduleJob> listByJobIds(@Param("jobIds") List<Long> jobIds);

    ScheduleJob getByJobKeyAndType(@Param("jobKey") String jobKey, @Param("type") int type);

    ScheduleJob getByJobKey(@Param("jobKey") String jobKey);

    List<Map<String, Object>> countByStatusAndType(@Param("type") Integer type, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("tenantId") Long tenantId, @Param("projectId") Long projectId, @Param("appType") Integer appType, @Param("dtuicTenantId") Long dtuicTenantId, @Param("statuses") List<Integer> status);

    List<Map<String, Object>> selectStatusAndType(@Param("type") Integer type, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("tenantId") Long tenantId, @Param("projectId") Long projectId, @Param("appType") Integer appType,
                                                  @Param("dtuicTenantId") Long dtuicTenantId, @Param("statuses") List<Integer> status, @Param("startPage") Integer startPage, @Param("pageSize") Integer pageSize);

    List<ScheduleJob> listJobByJobKeys(@Param("jobKeys") Collection<String> jobKeys);


    List<ScheduleJob> listIdByTaskIdAndStatus(@Param("taskId") Long taskId, @Param("statuses") List<Integer> status, @Param("appType") Integer appType,@Param("cycTime") String cycTime,@Param("type") Integer type);

    List<String> listJobIdByTaskIdAndStatus(@Param("taskId") Long taskId, @Param("appType") Integer appType, @Param("statuses") List<Integer> status);

    List<Map<String, String>> listTaskExeTimeInfo(@Param("taskId") Long taskId, @Param("statuses") List<Integer> status, @Param("pageQuery") PageQuery pageQuery, @Param("appType") Integer appType);

    ScheduleJob getByJobId(@Param("jobId") String jobId, @Param("isDeleted") Integer isDeleted);

    Long getId(@Param("jobId") String jobId);

    List<ScheduleJob> generalQuery(PageQuery<ScheduleJobDTO> pageQuery);

    List<ScheduleJob> listAfterOrBeforeJobs(@Param("taskId") Long taskId, @Param("isAfter") boolean isAfter, @Param("cycTime") String cycTime,@Param("appType") Integer appType,@Param("type") Integer type);

    Integer generalCount(@Param("model") ScheduleJobDTO object);

    List<StatusCount> getJobsStatusStatistics(@Param("model") ScheduleJobDTO object);

    Integer batchInsert(Collection batchJobs);

    Integer update(ScheduleJob scheduleJob);

    Integer updateStatusWithExecTime(ScheduleJob job);

    Integer updateNullTime(@Param("jobId") String jobId);

    List<String> listFillJobName(PageQuery<ScheduleJobDTO> pageQuery);

    Integer countFillJobNameDistinct(@Param("model") ScheduleJobDTO batchJobDTO);

    Integer countFillJobNameDistinctWithOutTask(@Param("model") ScheduleJobDTO batchJobDTO);

    Integer countFillJobName(@Param("model") ScheduleJobDTO batchJobDTO);

    List<String> listFillJobBizDate(@Param("model") ScheduleJobDTO dto);

    List<ScheduleJob> listNeedStopFillDataJob(@Param("fillDataJobName") String fillDataJobName, @Param("statusList") List<Integer> statusList,
                                              @Param("projectId") Long projectId, @Param("appType") Integer appType);

    List<Map<String, Object>> listTaskExeInfo(@Param("taskId") Long taskId, @Param("projectId") Long projectId, @Param("limitNum") int limitNum, @Param("appType") Integer appType);

    /**
     * 根据jobId获取子任务信息与任务状态
     *
     * @param jobId
     * @return
     */
    List<ScheduleJob> getSubJobsAndStatusByFlowId(@Param("jobId") String jobId);

    ScheduleJob getSubJobsAndStatusByFlowIdLimit(@Param("jobId") String jobId);

    /**
     * 获取补数据job的各状态的数量
     *
     * @param jobName
     * @return
     */
    List<Map<String, Long>> countFillDataAllStatusByJobName(@Param("jobName") String jobName, @Param("jobIds") List<String> jobIds);

    /**
     * 获取其中工作流类型的实例
     *
     * @param
     * @return
     */
    List<ScheduleJob> getWorkFlowSubJobId(@Param("jobIds") List<String> jobIds);

    /**
     * 根据工作流实例jobId获取全部子任务实例
     *
     * @param flowIds
     * @return
     */
    List<ScheduleJob> getSubJobsByFlowIds(@Param("flowIds") List<String> flowIds);

    Integer getTaskTypeByJobId(@Param("jobId") String jobId);

    List<String> getFlowJobIdsByJobName(@Param("jobName") String jobName);

    List<Map<String, Long>> countByFillDataAllStatus(@Param("fillIdList") List<Long> fillJobIdList, @Param("projectId") Long projectId, @Param("tenantId") Long tenantId, @Param("appType") Integer appType);

    List<Long> listFillIdList(PageQuery<ScheduleJobDTO> pageQuer);

    List<Long> listFillIdListWithOutTask(PageQuery<ScheduleJobDTO> pageQuery);

    List<ScheduleJob> queryFillData(PageQuery pageQuery);

    Integer countByFillData(@Param("model") ScheduleJobDTO batchJobDTO);

    ScheduleJob getWorkFlowTopNode(@Param("jobId") String jobId);

    List<ScheduleJob> listByJobIdList(@Param("jobIds") Collection<String> jobIds, @Param("projectId") Long projectId);

    List<String> listJobIdByTaskType(@Param("taskType") Integer taskType);

    Integer getStatusByJobId(@Param("jobId") String jobId);

    Integer countTasksByCycTimeTypeAndAddress(@Param("nodeAddress") String nodeAddress, @Param("scheduleType") Integer scheduleType, @Param("cycStartTime") String cycStartTime, @Param("cycEndTime") String cycEndTime);

    List<SimpleScheduleJobPO> listSimpleJobByStatusAddress(@Param("startId") Long startId, @Param("statuses") List<Integer> statuses, @Param("nodeAddress") String nodeAddress);

    Integer updateNodeAddress(@Param("nodeAddress") String nodeAddress, @Param("jobIds") List<String> ids);

    Integer updateJobStatusByIds(@Param("status") Integer status, @Param("jobIds") List<String> jobIds);

    void stopUnsubmitJob(@Param("likeName") String likeName, @Param("projectId") Long projectId, @Param("appType") Integer appType, @Param("status") Integer status);

    List<ScheduleJob> listExecJobByCycTimeTypeAddress(@Param("startId") Long startId, @Param("nodeAddress") String nodeAddress, @Param("scheduleType") Integer scheduleType, @Param("cycStartTime") String cycStartTime, @Param("cycEndTime") String cycEndTime, @Param("phaseStatus") Integer phaseStatus,
                                                      @Param("isEq") Boolean isEq, @Param("lastTime") Timestamp lastTime,@Param("isRestart") Integer isRestart);

    List<ScheduleJob> listExecJobByJobIds(@Param("nodeAddress") String nodeAddress,@Param("phaseStatus") Integer phaseStatus,@Param("isRestart") Integer isRestart,@Param("jobIds") Collection<String> jobIds);

    Integer updateJobInfoByJobId(@Param("jobId") String jobId, @Param("status") Integer status, @Param("execStartTime") Timestamp execStartTime, @Param("execEndTime") Timestamp execEndTime, @Param("execTime") Long execTime, @Param("retryNum") Integer retryNum,@Param("stopStatuses") List<Integer> stopStatuses);

    ScheduleJob getByTaskIdAndStatusOrderByIdLimit(@Param("taskId") Long taskId, @Param("status") Integer status, @Param("time") Timestamp time,@Param("appType") Integer appType);

    Integer updateStatusByJobId(@Param("jobId") String jobId, @Param("status") Integer status, @Param("logInfo") String logInfo, @Param("versionId") Integer versionId, @Param("execStartTime") Date execStartTime,@Param("execEndTime") Date execEndTime);

    List<ScheduleJob> listByBusinessDateAndPeriodTypeAndStatusList(PageQuery<ScheduleJobDTO> pageQuery);

    List<ScheduleJob> listJobByCyctimeAndJobName(@Param("preCycTime") String preCycTime, @Param("preJobName") String preJobName, @Param("scheduleType") Integer scheduleType);

    List<ScheduleJob> listJobByCyctimeAndJobNameBatch(@Param("startId") Long startId, @Param("preCycTime") String preCycTime, @Param("preJobName") String preJobName, @Param("scheduleType") Integer scheduleType, @Param("batchJobSize") Integer batchJobSize);

    Integer countJobByCyctimeAndJobName(@Param("preCycTime") String preCycTime, @Param("preJobName") String preJobName, @Param("scheduleType") Integer scheduleType);

    List<ScheduleJob> listJobsByTaskIdAndApptype(@Param("taskIds") List<Long> taskIds,@Param("appType") Integer appType);

    void deleteByJobKey(@Param("jobKeyList") List<String> jobKeyList);

    List<String> getAllNodeAddress();

    List<ScheduleJob> syncQueryJob(PageQuery<ScheduleJobDTO> pageQuery);


    Integer insert(ScheduleJob scheduleJob);

    void jobFail(@Param("jobId") String jobId, @Param("status") int status, @Param("logInfo") String logInfo);

    void updateJobStatus(@Param("jobId") String jobId, @Param("status") int status);

    void updateTaskStatusNotStopped(@Param("jobId") String jobId, @Param("status") int status, @Param("stopStatuses") List<Integer> stopStatuses);

    void updateJobPluginId(@Param("jobId") String jobId, @Param("pluginId") long pluginId);

    void updateJobStatusAndExecTime(@Param("jobId") String jobId, @Param("status") int status);

    void updateJobSubmitSuccess(@Param("jobId") String jobId, @Param("engineId") String engineId, @Param("appId") String appId, @Param("submitLog") String submitLog,@Param("jobGraph") String jobGraph);

    ScheduleJob getRdosJobByJobId(@Param("jobId") String jobId);

    List<ScheduleJob> getRdosJobByJobIds(@Param("jobIds")List<String> jobIds);

    void updateEngineLog(@Param("jobId")String jobId, @Param("engineLog")String engineLog);

    void updateRetryTaskParams(@Param("jobId")String jobId,  @Param("retryTaskParams")String retryTaskParams);

    ScheduleJob getByName(@Param("jobName") String jobName);

    void updateRetryNum(@Param("jobId")String jobId, @Param("retryNum")Integer retryNum);

    List<String> getJobIdsByStatus(@Param("status")Integer status, @Param("computeType")Integer computeType);

    List<ScheduleJob> listJobStatus(@Param("time") Timestamp timeStamp, @Param("computeType")Integer computeType,@Param("appType")Integer appType);

    Integer updateJobStatusByJobIds(@Param("jobIds") List<String> jobIds, @Param("status") Integer status);

    Integer updatePhaseStatusById(@Param("id") Long id, @Param("original") Integer original, @Param("update") Integer update);

    Long getListMinId(@Param("nodeAddress") String nodeAddress, @Param("scheduleType") Integer scheduleType, @Param("cycStartTime") String left, @Param("cycEndTime") String right, @Param("phaseStatus") Integer code,@Param("isRestart") Integer isRestart);

    Integer updateListPhaseStatus(@Param("jobIds") List<String> ids, @Param("update") Integer update);

    Integer updateJobStatusAndPhaseStatus(@Param("jobIds") List<String> jobIds, @Param("status") Integer status, @Param("phaseStatus") Integer phaseStatus,@Param("isRestart") Integer isRestart,@Param("nodeAddress") String nodeAddress);

    String getJobExtraInfo(@Param("jobId") String jobId);

    ScheduleJob getLastScheduleJob(@Param("taskId") Long taskId,@Param("id") Long id);

    void updateStatusByJobIdEqualsStatus(@Param("jobId") String jobId, @Param("status") Integer status, @Param("status1") Integer status1);

    void updateLogInfoByJobId(@Param("jobId") String jobId, @Param("msg") String msg);

    Integer updateJobStatusAndPhaseStatusByIds(@Param("jobIds") List<String> jobIds, @Param("status") Integer status, @Param("phaseStatus") Integer phaseStatus);

    List<SimpleScheduleJobPO> listJobByStatusAddressAndPhaseStatus(@Param("startId") Long startId, @Param("statuses") List<Integer> statuses, @Param("nodeAddress") String nodeAddress,@Param("phaseStatus") Integer phaseStatus);

    Integer updateFlowJob(@Param("placeholder") String placeholder, @Param("flowJob") String flowJob);
}