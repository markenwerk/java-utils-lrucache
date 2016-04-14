/*
 * Copyright (c) 2016 Torsten Krause, Markenwerk GmbH
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.markenwerk.utils.lrucache;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class LruCacheTests {

	@Test(expected=IllegalArgumentException.class)
	public void lru_negativeCacheSize() {

		new LruCache<String, Object>(-1);

	}

	@Test
	public void lru_evictWithoutListener() {

		LruCache<String, Object> cache = new LruCache<String, Object>(1);

		Object first = new Object();
		Object second = new Object();

		cache.put("1st", first);
		cache.put("2nd", second);

		Assert.assertEquals(1, cache.size());
		Assert.assertNull(cache.get("1st"));
		Assert.assertSame(second, cache.get("2nd"));

	}

	@Test
	public void lru_evictWithListener() {

		final List<Entry<String, Object>> evictions = new LinkedList<Entry<String, Object>>();
		LruCache<String, Object> cache = new LruCache<String, Object>(1, new LruCacheListener<String, Object>() {

			@Override
			public void onEvicted(Entry<String, Object> entry) {
				evictions.add(entry);
			}

		});

		Object first = new Object();
		Object second = new Object();

		cache.put("1st", first);
		cache.put("2nd", second);

		Assert.assertEquals(1, evictions.size());
		Assert.assertEquals("1st", evictions.get(0).getKey());
		Assert.assertSame(first, evictions.get(0).getValue());

	}

}
