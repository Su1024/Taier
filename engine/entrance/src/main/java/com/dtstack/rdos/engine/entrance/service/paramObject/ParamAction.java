package com.dtstack.rdos.engine.entrance.service.paramObject;

/**
 * 
 * Reason: TODO ADD REASON(可选)
 * Date: 2017年03月03日 下午1:25:18
 * Company: www.dtstack.com
 * @author sishu.yss
 *
 */
public class ParamAction {

	private String taskId;
	
	private String engineTaskId;
	
	private String name;
	
    private Integer taskType;
    
    private Integer computeType;
	
	private Integer isRestoration;
	
	private String sqlText;
	
	private String taskParams;
	
	/**
	 * 0 是有web端发起，1是有内部节点发起，如果是1就会直接执行不会再判断node运行的task任务在进行路由选择
	 */
	private Integer requestStart = 0;
	
	
	public Integer getRequestStart() {
		return requestStart;
	}

	public void setRequestStart(Integer requestStart) {
		this.requestStart = requestStart;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public Integer getComputeType() {
		return computeType;
	}

	public void setComputeType(Integer computeType) {
		this.computeType = computeType;
	}

	public String getSqlText() {
		return sqlText;
	}

	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}

	public String getTaskParams() {
		return taskParams;
	}

	public void setTaskParams(String taskParams) {
		this.taskParams = taskParams;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getEngineTaskId() {
		return engineTaskId;
	}

	public void setEngineTaskId(String engineTaskId) {
		this.engineTaskId = engineTaskId;
	}

	public Integer getIsRestoration() {
		return isRestoration;
	}

	public void setIsRestoration(Integer isRestoration) {
		this.isRestoration = isRestoration;
	}
	
}
