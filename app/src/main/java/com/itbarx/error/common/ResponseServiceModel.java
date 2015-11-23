package com.itbarx.error.common;

import java.io.InputStream;
import java.util.EventObject;
import java.util.List;

import org.apache.http.NameValuePair;

public class ResponseServiceModel<T> extends EventObject {

	/**
	 * Response Service Event Type
	 */
	private static final long serialVersionUID = 1L;

	public ResponseServiceModel(Object source, String serviceName, String data, Boolean serviceStatus, String errorMessage) {
	super(source);
	_serviceName = serviceName;
	responseData = data;
	this.serviceStatus = serviceStatus;
	setErrorMessage(errorMessage);

	}

	private String _serviceName;
	private String responseData;
	private Boolean serviceStatus;
	private String errorMessage;

	private String sendData;

	private T Model;
	private List<T> ListModel;
	private String methodName;
	private boolean isArrived;
	private InputStream stream;

	public String getResponseData() {
	return responseData;
	}

	public void setResponseData(String reequestData) {
	this.responseData = reequestData;
	}

	public String getServiceName() {
	return _serviceName;
	}

	public void setServiceName(String serviceName) {
	this._serviceName = serviceName;
	}

	public Boolean getServiceStatus() {
	return serviceStatus;
	}

	public void setServiceStatus(Boolean serviceStatus) {
	this.serviceStatus = serviceStatus;
	}

	public void setMethodName(String method) {
	methodName = method;
	}

	public String getMethodName() {
	return methodName;
	}

	public String getErrorMessage() {
	return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
	}

	public T getModel() {
	return Model;
	}

	public void setModel(T tInfo) {
	this.Model = tInfo;
	}

	public List<T> getListModel() {
	return ListModel;
	}

	public void setListInfoType(List<T> tListModel) {
	this.ListModel = tListModel;
	}

	public String getSendData() {
	return sendData;
	}

	public void setSendData(String sendData) {
	this.sendData = sendData;
	}

	List<NameValuePair> SenTParamters;

	public void setSentParams(List<NameValuePair> SentParamters) {
	SenTParamters = SentParamters;
	}

	public List<NameValuePair> getSentParams() {
	return SenTParamters;
	}

	/**
	 * @return the stream
	 */
	public InputStream getStream() {
	return stream;
	}

	/**
	 * @param stream
	 *          the stream to set
	 */
	public void setStream(InputStream stream) {
	this.stream = stream;
	}

	/**
	 * @return the isArrived
	 */
	public boolean getIsArrived() {
	return isArrived;
	}

	/**
	 * @param isArrived
	 *          the isArrived to set
	 */
	public void setIsArrived(boolean isArrived) {
	this.isArrived = isArrived;
	}

}
