package com.kvc.joy.core.ehcache.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kvc.joy.core.persistence.entity.UuidCrudEntity;

/**
 * 
 * @author 唐玮琳
 * @time 2013-1-2 下午6:57:49
 */
@Entity
@Table(name = "T_SYS_CACHE_CFG")
public class TSysCacheCfg extends UuidCrudEntity {

	private String cacheName; // cache的名字，用来识别不同的cache，必须惟一
	private int maxElementsInMemory; // 缓存中允许创建的最大对象数
	private int maxElementsOnDisk; // 硬盘管理的缓存元素数量最大限值。默认值为0，就是没有限制。
	private boolean eternal; // 缓存中对象是否为永久的，如果是，超时设置将被忽略，对象从不过期
	private boolean overflowToDisk; // 内存不足时，是否启用磁盘缓存
	
	private int timeToIdleSeconds; // 缓存数据的钝化时间，也就是在一个元素消亡之前，两次访问时间的最大时间间隔值，这只能在元素不是永久驻留时有效，如果该值是0 就意味着元素可以停顿无穷长的时间。
	private int timeToLiveSeconds; // 缓存数据的生存时间，也就是一个元素从构建到消亡的最大时间间隔值，这只能在元素不是永久驻留时有效，如果该值是0就意味着元素可以停顿无穷长的时间。
	private String memoryStoreEvictionPolicy; // 缓存满了之后的淘汰算法。共有三种，Recently Used (LRU)最近最少使用，为默认。First In First Out (FIFO)，先进先出。Less Frequently Used(specified as LFU)最少使用。
	private boolean diskPersistent; // 设定在虚拟机重启时是否进行磁盘存储，默认为false.(一般对于安全小型应用，宜设为true)。
	private int diskExpiryThreadIntervalSeconds; // 访问磁盘线程活动时间。
	private int diskSpoolBufferSizeMB; // 存入磁盘时的缓冲区大小，默认30MB,每个缓存都有自己的缓冲区。

	@Column(nullable=false)
	public int getMaxElementsInMemory() {
		return maxElementsInMemory;
	}

	public void setMaxElementsInMemory(int maxElementsInMemory) {
		this.maxElementsInMemory = maxElementsInMemory;
	}

	@Column(nullable=false)
	public boolean isEternal() {
		return eternal;
	}

	public void setEternal(boolean eternal) {
		this.eternal = eternal;
	}

	public int getTimeToIdleSeconds() {
		return timeToIdleSeconds;
	}

	public void setTimeToIdleSeconds(int timeToIdleSeconds) {
		this.timeToIdleSeconds = timeToIdleSeconds;
	}

	public int getTimeToLiveSeconds() {
		return timeToLiveSeconds;
	}

	public void setTimeToLiveSeconds(int timeToLiveSeconds) {
		this.timeToLiveSeconds = timeToLiveSeconds;
	}

	@Column(nullable=false)
	public boolean isOverflowToDisk() {
		return overflowToDisk;
	}

	public void setOverflowToDisk(boolean overflowToDisk) {
		this.overflowToDisk = overflowToDisk;
	}

	@Column(length = 8)
	public String getMemoryStoreEvictionPolicy() {
		return memoryStoreEvictionPolicy;
	}

	public void setMemoryStoreEvictionPolicy(String memoryStoreEvictionPolicy) {
		this.memoryStoreEvictionPolicy = memoryStoreEvictionPolicy;
	}

	@Column(nullable=false, unique=true, length=64)
	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	@Column(nullable=false)
	public int getMaxElementsOnDisk() {
		return maxElementsOnDisk;
	}

	public void setMaxElementsOnDisk(int maxElementsOnDisk) {
		this.maxElementsOnDisk = maxElementsOnDisk;
	}

	public boolean isDiskPersistent() {
		return diskPersistent;
	}

	public void setDiskPersistent(boolean diskPersistent) {
		this.diskPersistent = diskPersistent;
	}

	public int getDiskExpiryThreadIntervalSeconds() {
		return diskExpiryThreadIntervalSeconds;
	}

	public void setDiskExpiryThreadIntervalSeconds(int diskExpiryThreadIntervalSeconds) {
		this.diskExpiryThreadIntervalSeconds = diskExpiryThreadIntervalSeconds;
	}

	public int getDiskSpoolBufferSizeMB() {
		return diskSpoolBufferSizeMB;
	}

	public void setDiskSpoolBufferSizeMB(int diskSpoolBufferSizeMB) {
		this.diskSpoolBufferSizeMB = diskSpoolBufferSizeMB;
	}
	
}
