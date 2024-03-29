/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.access.config;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import org.springframework.session.Session;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionManager;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.session.web.http.MultiHttpSessionStrategy;
import org.springframework.util.Assert;

/**
 *
 * @author s
 */
public class CustomHeaderHttpSessionStrategy  implements MultiHttpSessionStrategy,HttpSessionStrategy {
	private String headerName = "x-auth-token";

    CustomHeaderHttpSessionStrategy() { }

  

    @Override
    public String getRequestedSessionId(HttpServletRequest request) {
        // cookie part
       Map<String, String> sessionIds = getSessionIds(request);
        if(! sessionIds.isEmpty())
        {
		String sessionAlias = getCurrentSessionAlias(request);
		return sessionIds.get(sessionAlias);
        }
        
// header part
        String sessionId = request.getHeader(headerName);
            return sessionId;


    }

    @Override
    public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader(this.headerName, session.getId());
                Set<String> sessionIdsWritten = getSessionIdsWritten(request);
		if (sessionIdsWritten.contains(session.getId())) {
			return;
		}
		sessionIdsWritten.add(session.getId());

		Map<String, String> sessionIds = getSessionIds(request);
		String sessionAlias = getCurrentSessionAlias(request);
		sessionIds.put(sessionAlias, session.getId());

		String cookieValue = createSessionCookieValue(sessionIds);
		this.cookieSerializer
				.writeCookieValue(new CookieSerializer.CookieValue(request, response, cookieValue));
                
                
    }

    @Override
    public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response) {
        // header part
    response.setHeader(headerName, "");

    // cookie part
   Map<String, String> sessionIds = getSessionIds(request);
		String requestedAlias = getCurrentSessionAlias(request);
		sessionIds.remove(requestedAlias);

		String cookieValue = createSessionCookieValue(sessionIds);
		this.cookieSerializer
				.writeCookieValue(new CookieSerializer.CookieValue(request, response, cookieValue));
    }
    
    
    
    
    	/**
	 * The default delimiter for both serialization and deserialization.
	 */
	private static final String DEFAULT_DELIMITER = " ";

	private static final String SESSION_IDS_WRITTEN_ATTR = CookieHttpSessionStrategy.class
			.getName().concat(".SESSIONS_WRITTEN_ATTR");

	static final String DEFAULT_ALIAS = "0";

	static final String DEFAULT_SESSION_ALIAS_PARAM_NAME = "_s";

	private static final Pattern ALIAS_PATTERN = Pattern.compile("^[\\w-]{1,50}$");

	private String sessionParam = DEFAULT_SESSION_ALIAS_PARAM_NAME;

	private CookieSerializer cookieSerializer = new CustomCookieSerializer();

	/**
	 * The delimiter between a session alias and a session id when reading a cookie value.
	 * The default value is " ".
	 */
	private String deserializationDelimiter = DEFAULT_DELIMITER;

	/**
	 * The delimiter between a session alias and a session id when writing a cookie value.
	 * The default is " ".
	 */
	private String serializationDelimiter = DEFAULT_DELIMITER;


	public String getCurrentSessionAlias(HttpServletRequest request) {
		if (this.sessionParam == null) {
			return DEFAULT_ALIAS;
		}
		String u = request.getParameter(this.sessionParam);
		if (u == null) {
			return DEFAULT_ALIAS;
		}
		if (!ALIAS_PATTERN.matcher(u).matches()) {
			return DEFAULT_ALIAS;
		}
		return u;
	}

	public String getNewSessionAlias(HttpServletRequest request) {
		Set<String> sessionAliases = getSessionIds(request).keySet();
		if (sessionAliases.isEmpty()) {
			return DEFAULT_ALIAS;
		}
		long lastAlias = Long.decode(DEFAULT_ALIAS);
		for (String alias : sessionAliases) {
			long selectedAlias = safeParse(alias);
			if (selectedAlias > lastAlias) {
				lastAlias = selectedAlias;
			}
		}
		return Long.toHexString(lastAlias + 1);
	}

	private long safeParse(String hex) {
		try {
			return Long.decode("0x" + hex);
		}
		catch (NumberFormatException notNumber) {
			return 0;
		}
	}



	@SuppressWarnings("unchecked")
	private Set<String> getSessionIdsWritten(HttpServletRequest request) {
		Set<String> sessionsWritten = (Set<String>) request
				.getAttribute(SESSION_IDS_WRITTEN_ATTR);
		if (sessionsWritten == null) {
			sessionsWritten = new HashSet<String>();
			request.setAttribute(SESSION_IDS_WRITTEN_ATTR, sessionsWritten);
		}
		return sessionsWritten;
	}

	private String createSessionCookieValue(Map<String, String> sessionIds) {
		if (sessionIds.isEmpty()) {
			return "";
		}
		if (sessionIds.size() == 1 && sessionIds.keySet().contains(DEFAULT_ALIAS)) {
			return sessionIds.values().iterator().next();
		}

		StringBuffer buffer = new StringBuffer();
		for (Map.Entry<String, String> entry : sessionIds.entrySet()) {
			String alias = entry.getKey();
			String id = entry.getValue();

			buffer.append(alias);
			buffer.append(this.serializationDelimiter);
			buffer.append(id);
			buffer.append(this.serializationDelimiter);
		}
		buffer.deleteCharAt(buffer.length() - 1);
		return buffer.toString();
	}

	

	/**
	 * Sets the name of the HTTP parameter that is used to specify the session alias. If
	 * the value is null, then only a single session is supported per browser.
	 *
	 * @param sessionAliasParamName the name of the HTTP parameter used to specify the
	 * session alias. If null, then ony a single session is supported per browser.
	 */
	public void setSessionAliasParamName(String sessionAliasParamName) {
		this.sessionParam = sessionAliasParamName;
	}

	/**
	 * Sets the {@link CookieSerializer} to be used.
	 *
	 * @param cookieSerializer the cookieSerializer to set. Cannot be null.
	 */
	public void setCookieSerializer(CookieSerializer cookieSerializer) {
		Assert.notNull(cookieSerializer, "cookieSerializer cannot be null");
		this.cookieSerializer = cookieSerializer;
	}

	/**
	 * Sets the name of the cookie to be used.
	 * @param cookieName the name of the cookie to be used
	 * @deprecated use {@link #setCookieSerializer(CookieSerializer)}
	 */
	@Deprecated
	public void setCookieName(String cookieName) {
		DefaultCookieSerializer serializer = new DefaultCookieSerializer();
		serializer.setCookieName(cookieName);
		this.cookieSerializer = serializer;
	}

	/**
	 * Sets the delimiter between a session alias and a session id when deserializing a
	 * cookie. The default is " " This is useful when using
	 * <a href="https://tools.ietf.org/html/rfc6265">RFC 6265</a> for writing the cookies
	 * which doesn't allow for spaces in the cookie values.
	 *
	 * @param delimiter the delimiter to set (i.e. "_ " will try a delimeter of either "_"
	 * or " ")
	 */
	public void setDeserializationDelimiter(String delimiter) {
		this.deserializationDelimiter = delimiter;
	}

	/**
	 * Sets the delimiter between a session alias and a session id when deserializing a
	 * cookie. The default is " ". This is useful when using
	 * <a href="https://tools.ietf.org/html/rfc6265">RFC 6265</a> for writing the cookies
	 * which doesn't allow for spaces in the cookie values.
	 *
	 * @param delimiter the delimiter to set (i.e. "_")
	 */
	public void setSerializationDelimiter(String delimiter) {
		this.serializationDelimiter = delimiter;
	}

	public Map<String, String> getSessionIds(HttpServletRequest request) {
		List<String> cookieValues = this.cookieSerializer.readCookieValues(request);
		String sessionCookieValue = cookieValues.isEmpty() ? ""
				: cookieValues.iterator().next();
		Map<String, String> result = new LinkedHashMap<String, String>();
		StringTokenizer tokens = new StringTokenizer(sessionCookieValue,
				this.deserializationDelimiter);
		if (tokens.countTokens() == 1) {
			result.put(DEFAULT_ALIAS, tokens.nextToken());
			return result;
		}
		while (tokens.hasMoreTokens()) {
			String alias = tokens.nextToken();
			if (!tokens.hasMoreTokens()) {
				break;
			}
			String id = tokens.nextToken();
			result.put(alias, id);
		}
		return result;
	}

	public HttpServletRequest wrapRequest(HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute(HttpSessionManager.class.getName(), this);
		return request;
	}

	public HttpServletResponse wrapResponse(HttpServletRequest request,
			HttpServletResponse response) {
		return new MultiSessionHttpServletResponse(response, request);
	}

	public String encodeURL(String url, String sessionAlias) {
		String encodedSessionAlias = urlEncode(sessionAlias);
		int queryStart = url.indexOf("?");
		boolean isDefaultAlias = DEFAULT_ALIAS.equals(encodedSessionAlias);
		if (queryStart < 0) {
			return isDefaultAlias ? url
					: url + "?" + this.sessionParam + "=" + encodedSessionAlias;
		}
		String path = url.substring(0, queryStart);
		String query = url.substring(queryStart + 1, url.length());
		String replacement = isDefaultAlias ? "" : "$1" + encodedSessionAlias;
		query = query.replaceFirst("((^|&)" + this.sessionParam + "=)([^&]+)?",
				replacement);
		String sessionParamReplacement = String.format("%s=%s", this.sessionParam,
				encodedSessionAlias);

		if (!isDefaultAlias && !query.contains(sessionParamReplacement)
				&& url.endsWith(query)) {
			// no existing alias
			if (!(query.endsWith("&") || query.length() == 0)) {
				query += "&";
			}
			query += sessionParamReplacement;
		}

		return path + "?" + query;
	}

	private String urlEncode(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

    private class CustomCookieSerializer extends DefaultCookieSerializer  {

        public CustomCookieSerializer() {            
		setCookieName("x-auth-token");
		setCookiePath("/");
		setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        }
    }

	/**
	 * A {@link CookieHttpSessionStrategy} aware {@link HttpServletResponseWrapper}.
	 */
	class MultiSessionHttpServletResponse extends HttpServletResponseWrapper {
		private final HttpServletRequest request;

		MultiSessionHttpServletResponse(HttpServletResponse response,
				HttpServletRequest request) {
			super(response);
			this.request = request;
		}

		private String getCurrentSessionAliasFromUrl(String url) {
			String currentSessionAliasFromUrl = null;
			int queryStart = url.indexOf("?");

			if (queryStart >= 0) {
				String query = url.substring(queryStart + 1);
				Matcher matcher = Pattern
						.compile(String.format("%s=([^&]+)",
								CustomHeaderHttpSessionStrategy.this.sessionParam))
						.matcher(query);

				if (matcher.find()) {
					currentSessionAliasFromUrl = matcher.group(1);
				}
			}

			return currentSessionAliasFromUrl;
		}

		@Override
		public String encodeRedirectURL(String url) {
			String encodedUrl = super.encodeRedirectURL(url);
			String currentSessionAliasFromUrl = getCurrentSessionAliasFromUrl(encodedUrl);
			String alias = (currentSessionAliasFromUrl != null)
					? currentSessionAliasFromUrl : getCurrentSessionAlias(this.request);

			return CustomHeaderHttpSessionStrategy.this.encodeURL(encodedUrl, alias);
		}

		@Override
		public String encodeURL(String url) {
			String encodedUrl = super.encodeURL(url);
			String currentSessionAliasFromUrl = getCurrentSessionAliasFromUrl(encodedUrl);
			String alias = (currentSessionAliasFromUrl != null)
					? currentSessionAliasFromUrl : getCurrentSessionAlias(this.request);

			return CustomHeaderHttpSessionStrategy.this.encodeURL(encodedUrl, alias);
		}
                
                
                  public CookieSerializer getCustomCookieSerializer() {
		DefaultCookieSerializer serializer = new DefaultCookieSerializer();
		serializer.setCookieName("x-auth-token");
		serializer.setCookiePath("/");
		serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
		return serializer;
	}
	}

    

    
}
