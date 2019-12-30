package com.example.update.record.domain;


import com.example.update.record.annotation.Update;

import java.util.List;
import java.util.Set;

public class Resource {
    private Long id;

    private String rsNo;

    private String url;

    @Update(field = true, readConverterExp = "1=删除|2=正常|3=停用")
    private String path;

    private Integer depth;

    @Update(remark = "名称")
    private String name;

    private Integer seqNum;

    private String iconPath;

    private String parentNo;

    private List<Resource> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRsNo() {
        return rsNo;
    }

    public void setRsNo(String rsNo) {
        this.rsNo = rsNo == null ? null : rsNo.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath == null ? null : iconPath.trim();
    }

    public String getParentNo() {
        return parentNo;
    }

    public void setParentNo(String parentNo) {
        this.parentNo = parentNo == null ? null : parentNo.trim();
    }

    public List<Resource> getChildren() {
        return children;
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }

}