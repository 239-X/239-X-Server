package com.minimal.common.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 数据传输对象示例
 * @author wubin
 *
 */
@ApiModel("数据传输对象示例")
public class SampleDto {

	@ApiModelProperty("ID标识（作为单个资源操作的入参可不填）")
	private Long id;
	
	@ApiModelProperty("名")
	private String firstName;
	
	@ApiModelProperty("姓")
	private String lastName;
	
	@ApiModelProperty("年龄")
	private Integer age;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "SampleVo [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "]";
	}
}
