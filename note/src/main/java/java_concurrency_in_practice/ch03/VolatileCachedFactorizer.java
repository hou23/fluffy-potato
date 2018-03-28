package java_concurrency_in_practice.ch03;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.math.BigInteger;

/**
 * @author yaojie.hou
 * @create 2018/3/15
 */
@ThreadSafe
public class VolatileCachedFactorizer implements Servlet {

	private volatile OneValueCache cache = new OneValueCache(null, null);

	@Override
	public void service(ServletRequest req, ServletResponse resp) {
		BigInteger i = extractFromRequest(req);
		BigInteger[] factors = cache.getFactors(i);
		if (factors == null) {
			factors = factor(i);
			cache = new OneValueCache(i, factors);
		}
		encodeIntoResponse(resp, factors);
	}

	void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
	}

	BigInteger extractFromRequest(ServletRequest req) {
		return new BigInteger("7");
	}

	BigInteger[] factor(BigInteger i) {
		// Doesn't really factor
		return new BigInteger[]{i};
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void destroy() {

	}
}
