package com.java04.wibucian.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "flyway_schema_history")
public class FlywaySchemaHistory {
    @Id
    @Column(name = "installed_rank", nullable = false)
    private Integer id;

    @Column(name = "version", length = 50)
    private String version;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Column(name = "script", nullable = false, length = 1000)
    private String script;

    @Column(name = "checksum")
    private Integer checksum;

    @Column(name = "installed_by", nullable = false, length = 100)
    private String installedBy;

    @Column(name = "installed_on", nullable = false)
    private Instant installedOn;

    @Column(name = "execution_time", nullable = false)
    private Integer executionTime;

    @Column(name = "success", nullable = false)
    private Boolean success = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Integer getChecksum() {
        return checksum;
    }

    public void setChecksum(Integer checksum) {
        this.checksum = checksum;
    }

    public String getInstalledBy() {
        return installedBy;
    }

    public void setInstalledBy(String installedBy) {
        this.installedBy = installedBy;
    }

    public Instant getInstalledOn() {
        return installedOn;
    }

    public void setInstalledOn(Instant installedOn) {
        this.installedOn = installedOn;
    }

    public Integer getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Integer executionTime) {
        this.executionTime = executionTime;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}