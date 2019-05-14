/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connector.roostify.api.header;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"Status",
		"Server",
		"Access-Control-Allow-Origin",
		"X-Ua-Compatible",
		"X-Request-Id",
		"X-Runtime",
		"Connection",
		"Pragma",
		"P3p",
		"Date",
		"Via",
		"X-Frame-Options",
		"Strict-Transport-Security",
		"Cache-Control",
		"Vary",
		"Set-Cookie",
		"Expires",
		"Content-Language",
		"Content-Type"
})
public class HeaderDTO {

		@JsonProperty("Status")
		private List<String> status = null;
		@JsonProperty("Server")
		private List<String> server = null;
		@JsonProperty("Access-Control-Allow-Origin")
		private List<String> accessControlAllowOrigin = null;
		@JsonProperty("X-Ua-Compatible")
		private List<String> xUaCompatible = null;
		@JsonProperty("X-Request-Id")
		private List<String> xRequestId = null;
		@JsonProperty("X-Runtime")
		private List<String> xRuntime = null;
		@JsonProperty("Connection")
		private List<String> connection = null;
		@JsonProperty("Pragma")
		private List<String> pragma = null;
		@JsonProperty("P3p")
		private List<String> p3p = null;
		@JsonProperty("Date")
		private List<String> date = null;
		@JsonProperty("Via")
		private List<String> via = null;
		@JsonProperty("X-Frame-Options")
		private List<String> xFrameOptions = null;
		@JsonProperty("Strict-Transport-Security")
		private List<String> strictTransportSecurity = null;
		@JsonProperty("Cache-Control")
		private List<String> cacheControl = null;
		@JsonProperty("Vary")
		private List<String> vary = null;
		@JsonProperty("Set-Cookie")
		private List<String> setCookie = null;
		@JsonProperty("Expires")
		private List<String> expires = null;
		@JsonProperty("Content-Language")
		private List<String> contentLanguage = null;
		@JsonProperty("Content-Type")
		private List<String> contentType = null;
		@JsonIgnore
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		@JsonProperty("Status")
		public List<String> getStatus() {
			return status;
		}

		@JsonProperty("Status")
		public void setStatus(List<String> status) {
			this.status = status;
		}

		@JsonProperty("Server")
		public List<String> getServer() {
			return server;
		}

		@JsonProperty("Server")
		public void setServer(List<String> server) {
			this.server = server;
		}

		@JsonProperty("Access-Control-Allow-Origin")
		public List<String> getAccessControlAllowOrigin() {
			return accessControlAllowOrigin;
		}

		@JsonProperty("Access-Control-Allow-Origin")
		public void setAccessControlAllowOrigin(List<String> accessControlAllowOrigin) {
			this.accessControlAllowOrigin = accessControlAllowOrigin;
		}

		@JsonProperty("X-Ua-Compatible")
		public List<String> getXUaCompatible() {
			return xUaCompatible;
		}

		@JsonProperty("X-Ua-Compatible")
		public void setXUaCompatible(List<String> xUaCompatible) {
			this.xUaCompatible = xUaCompatible;
		}

		@JsonProperty("X-Request-Id")
		public List<String> getXRequestId() {
			return xRequestId;
		}

		@JsonProperty("X-Request-Id")
		public void setXRequestId(List<String> xRequestId) {
			this.xRequestId = xRequestId;
		}

		@JsonProperty("X-Runtime")
		public List<String> getXRuntime() {
			return xRuntime;
		}

		@JsonProperty("X-Runtime")
		public void setXRuntime(List<String> xRuntime) {
			this.xRuntime = xRuntime;
		}

		@JsonProperty("Connection")
		public List<String> getConnection() {
			return connection;
		}

		@JsonProperty("Connection")
		public void setConnection(List<String> connection) {
			this.connection = connection;
		}

		@JsonProperty("Pragma")
		public List<String> getPragma() {
			return pragma;
		}

		@JsonProperty("Pragma")
		public void setPragma(List<String> pragma) {
			this.pragma = pragma;
		}

		@JsonProperty("P3p")
		public List<String> getP3p() {
			return p3p;
		}

		@JsonProperty("P3p")
		public void setP3p(List<String> p3p) {
			this.p3p = p3p;
		}

		@JsonProperty("Date")
		public List<String> getDate() {
			return date;
		}

		@JsonProperty("Date")
		public void setDate(List<String> date) {
			this.date = date;
		}

		@JsonProperty("Via")
		public List<String> getVia() {
			return via;
		}

		@JsonProperty("Via")
		public void setVia(List<String> via) {
			this.via = via;
		}

		@JsonProperty("X-Frame-Options")
		public List<String> getXFrameOptions() {
			return xFrameOptions;
		}

		@JsonProperty("X-Frame-Options")
		public void setXFrameOptions(List<String> xFrameOptions) {
			this.xFrameOptions = xFrameOptions;
		}

		@JsonProperty("Strict-Transport-Security")
		public List<String> getStrictTransportSecurity() {
			return strictTransportSecurity;
		}

		@JsonProperty("Strict-Transport-Security")
		public void setStrictTransportSecurity(List<String> strictTransportSecurity) {
			this.strictTransportSecurity = strictTransportSecurity;
		}

		@JsonProperty("Cache-Control")
		public List<String> getCacheControl() {
			return cacheControl;
		}

		@JsonProperty("Cache-Control")
		public void setCacheControl(List<String> cacheControl) {
			this.cacheControl = cacheControl;
		}

		@JsonProperty("Vary")
		public List<String> getVary() {
			return vary;
		}

		@JsonProperty("Vary")
		public void setVary(List<String> vary) {
			this.vary = vary;
		}

		@JsonProperty("Set-Cookie")
		public List<String> getSetCookie() {
			return setCookie;
		}

		@JsonProperty("Set-Cookie")
		public void setSetCookie(List<String> setCookie) {
			this.setCookie = setCookie;
		}

		@JsonProperty("Expires")
		public List<String> getExpires() {
			return expires;
		}

		@JsonProperty("Expires")
		public void setExpires(List<String> expires) {
			this.expires = expires;
		}

		@JsonProperty("Content-Language")
		public List<String> getContentLanguage() {
			return contentLanguage;
		}

		@JsonProperty("Content-Language")
		public void setContentLanguage(List<String> contentLanguage) {
			this.contentLanguage = contentLanguage;
		}

		@JsonProperty("Content-Type")
		public List<String> getContentType() {
			return contentType;
		}

		@JsonProperty("Content-Type")
		public void setContentType(List<String> contentType) {
			this.contentType = contentType;
		}

		@JsonAnyGetter
		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		@JsonAnySetter
		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}


