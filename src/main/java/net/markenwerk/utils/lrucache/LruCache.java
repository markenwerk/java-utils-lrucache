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

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * A {@link LruCache} is a {@link LinkedHashMap} that is configured to hold it's
 * {@link Entry Entries} in access order and evicts the least recently accessed
 * {@link Entry}, if the configured cache size is exceeded.
 * 
 * @param <Key>
 *           The key type.
 * @param <Value>
 *           The value type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class LruCache<Key, Value> extends LinkedHashMap<Key, Value> {

	private static final long serialVersionUID = 580280319605735218L;

	private static final float DEFAULT_LOAD_FACTOR = 0.75f;

	private final int cacheSize;

	private final LruCacheListener<Key, Value> listener;

	/**
	 * Creates a new {@link LruCache} without a {@link LruCacheListener}.
	 * 
	 * @param cacheSize
	 *           The cache size to be used.
	 * 
	 * @throws IllegalArgumentException
	 *            If the given cache size is negative.
	 */
	public LruCache(int cacheSize) throws IllegalArgumentException {
		this(cacheSize, null);
	}

	/**
	 * Creates a new {@link LruCache}.
	 * 
	 * @param cacheSize
	 *           The cache size to be used.
	 * @param listener
	 *           The {@link LruCacheListener} to be used, or {@literal null}, if
	 *           no {@link LruCacheListener} should be used.
	 * 
	 * @throws IllegalArgumentException
	 *            If the given cache size is negative.
	 */
	public LruCache(int cacheSize, LruCacheListener<Key, Value> listener) throws IllegalArgumentException {
		super(cacheSize, DEFAULT_LOAD_FACTOR, true);
		if (cacheSize < 0) {
			throw new IllegalArgumentException("cacheSize is negative");
		}
		this.cacheSize = cacheSize;
		this.listener = listener;
	}

	@Override
	protected boolean removeEldestEntry(Entry<Key, Value> eldest) {
		if (size() > cacheSize) {
			if (null != listener) {
				listener.onEvicted(eldest);
			}
			return true;
		} else {
			return false;
		}
	}

}