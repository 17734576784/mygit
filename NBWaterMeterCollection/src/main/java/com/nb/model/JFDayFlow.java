package com.nb.model;

import java.io.Serializable;

import com.nb.utils.Constant;

/** 
* @ClassName: JFDayFlow 
* @Description: 流量计日流量数据表
* @author dbr
* @date 2019年4月17日 上午8:52:26 
*  
*/
public class JFDayFlow implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -7042503292961912847L;

	private Integer rtuId;

    private Short mpId;

    private Integer date;

    private Integer time;

    private Double ljllZx;

    private Double ljrlZx;

    private Double ljllFx;

    private Double ljrlFx;

    private Double llLjllZx;

    private Double llLjllFx;

    private Double rlLjrlZx;

    private Double rlLjrlFx;

    private Integer ljsjRun;

    private Integer nowTime;

    private Integer st;

    private Byte source;
    
    private String tableName;

    /**
  	 * @return the tableName
  	 */
  	public String getTableName() {
  		return tableName;
  	}

  	/**
  	 * @param tableName the tableName to set
  	 */
  	public void setTableName(String date) {
  		if (date.length() == Constant.TABLE_YYYYMM) {
  			this.tableName = "yddata.dbo.JFDayFlow" + date;
  		} else {
  			this.tableName = date;
  		}
  	}
  	
    public Integer getRtuId() {
        return rtuId;
    }

    public void setRtuId(Integer rtuId) {
        this.rtuId = rtuId;
    }

    public Short getMpId() {
        return mpId;
    }

    public void setMpId(Short mpId) {
        this.mpId = mpId;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Double getLjllZx() {
        return ljllZx;
    }

    public void setLjllZx(Double ljllZx) {
        this.ljllZx = ljllZx;
    }

    public Double getLjrlZx() {
        return ljrlZx;
    }

    public void setLjrlZx(Double ljrlZx) {
        this.ljrlZx = ljrlZx;
    }

    public Double getLjllFx() {
        return ljllFx;
    }

    public void setLjllFx(Double ljllFx) {
        this.ljllFx = ljllFx;
    }

    public Double getLjrlFx() {
        return ljrlFx;
    }

    public void setLjrlFx(Double ljrlFx) {
        this.ljrlFx = ljrlFx;
    }

    public Double getLlLjllZx() {
        return llLjllZx;
    }

    public void setLlLjllZx(Double llLjllZx) {
        this.llLjllZx = llLjllZx;
    }

    public Double getLlLjllFx() {
        return llLjllFx;
    }

    public void setLlLjllFx(Double llLjllFx) {
        this.llLjllFx = llLjllFx;
    }

    public Double getRlLjrlZx() {
        return rlLjrlZx;
    }

    public void setRlLjrlZx(Double rlLjrlZx) {
        this.rlLjrlZx = rlLjrlZx;
    }

    public Double getRlLjrlFx() {
        return rlLjrlFx;
    }

    public void setRlLjrlFx(Double rlLjrlFx) {
        this.rlLjrlFx = rlLjrlFx;
    }

    public Integer getLjsjRun() {
        return ljsjRun;
    }

    public void setLjsjRun(Integer ljsjRun) {
        this.ljsjRun = ljsjRun;
    }

    public Integer getNowTime() {
        return nowTime;
    }

    public void setNowTime(Integer nowTime) {
        this.nowTime = nowTime;
    }

    public Integer getSt() {
        return st;
    }

    public void setSt(Integer st) {
        this.st = st;
    }

    public Byte getSource() {
        return source;
    }

    public void setSource(Byte source) {
        this.source = source;
    }
}