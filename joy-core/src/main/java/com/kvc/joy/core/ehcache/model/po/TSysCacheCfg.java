package com.kvc.joy.core.ehcache.model.po;

import com.kvc.joy.core.persistence.support.entity.UuidCrudEntity;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Comment;
import com.kvc.joy.core.persistence.orm.jpa.annotations.DefaultValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author 唐玮琳
 * @time 2013-1-2 下午6:57:49
 */
@Entity
@Table(name = "t_sys_cache_cfg")
@Comment("缓存配置")
public class TSysCacheCfg extends UuidCrudEntity {

	private String cacheName; // cache的名字，用来识别不同的cache，必须惟一
	private int maxElementsInMemory; // 缓存中允许创建的最大对象数
	private int maxElementsOnDisk; // 硬盘管理的缓存元素数量最大限值。默认值为0，就是没有限制。
	private String eternal; // 缓存中对象是否为永久的，如果是，超时设置将被忽略，对象从不过期
	private String overflowToDisk; // 内存不足时，是否启用磁盘缓存

	private int timeToIdleSeconds; // 缓存数据的钝化时间，也就是在一个元素消亡之前，两次访问时间的最大时间间隔值，这只能在元素不是永久驻留时有效，如果该值是0 就意味着元素可以停顿无穷长的时间。
	private int timeToLiveSeconds; // 缓存数据的生存时间，也就是一个元素从构建到消亡的最大时间间隔值，这只能在元素不是永久驻留时有效，如果该值是0就意味着元素可以停顿无穷长的时间。
	private String memoryStoreEvictionPolicy; // 缓存满了之后的淘汰算法。共有三种，Recently Used (LRU)最近最少使用，为默认。First In First Out
												// (FIFO)，先进先出。Less Frequently Used(specified as LFU)最少使用。
	private String diskPersistent; // 设定在虚拟机重启时是否进行磁盘存储，默认为false.(一般对于安全小型应用，宜设为true)。
	private int diskExpiryThreadIntervalSeconds; // 访问磁盘线程活动时间。
	private int diskSpoolBufferSizeMb; // 存入磁盘时的缓冲区大小，默认30MB,每个缓存都有自己的缓冲区。

	@Column(nullable = false)
	@Comment(value = "缓存最大对象数", detailDesc = "内存允许缓存的最大对象数")
	public int getMaxElementsInMemory() {
		return maxElementsInMemory;
	}

	public void setMaxElementsInMemory(int maxElementsInMemory) {
		this.maxElementsInMemory = maxElementsInMemory;
	}

	@Column(length = 1, nullable = false)
	@Comment(value = "是否永久缓存", detailDesc = "内存允许缓存的最大对象数")
	@DefaultValue("1")
	public String getEternal() {
		return eternal;
	}
	
	public boolean eternal() {
		return "1".equals(eternal);
	}

	public void setEternal(String eternal) {
		this.eternal = eternal;
	}

	@Column(nullable = false)
	@Comment(value = "钝化时间", detailDesc = "也就是在一个元素消亡之前，两次访问时间的最大时间间隔值，" + "这只能在元素不是永久驻留时有效，如果该值是0, 就意味着元素可以停顿无穷长的时间。")
	public int getTimeToIdleSeconds() {
		return timeToIdleSeconds;
	}

	public void setTimeToIdleSeconds(int timeToIdleSeconds) {
		this.timeToIdleSeconds = timeToIdleSeconds;
	}

	@Column(nullable = false)
	@Comment(value = "生存时间", detailDesc = "也就是一个元素从构建到消亡的最大时间间隔值，这只能在元素不是永久驻留时有效，" + "如果该值是0就意味着元素可以停顿无穷长的时间。")
	public int getTimeToLiveSeconds() {
		return timeToLiveSeconds;
	}

	public void setTimeToLiveSeconds(int timeToLiveSeconds) {
		this.timeToLiveSeconds = timeToLiveSeconds;
	}

	@Column(length = 1, nullable = false)
	@Comment(value = "是否启用磁盘缓存", detailDesc = "内存不足时，是否启用磁盘缓存")
	@DefaultValue("1")
	public String getOverflowToDisk() {
		return overflowToDisk;
	}
	
	public boolean overflowToDisk() {
		return "1".equals(overflowToDisk);
	}

	public void setOverflowToDisk(String overflowToDisk) {
		this.overflowToDisk = overflowToDisk;
	}

	@Column(length = 8, nullable = false)
	@DefaultValue("LRU")
	@Comment(value = "淘汰算法", detailDesc = "缓存满了之后的淘汰算法。共有三种，Recently Used (LRU)最近最少使用，为默认。"
			+ "First In First Out (FIFO)，先进先出。Less Frequently Used(specified as LFU)最少使用。")
	public String getMemoryStoreEvictionPolicy() {
		return memoryStoreEvictionPolicy;
	}

	public void setMemoryStoreEvictionPolicy(String memoryStoreEvictionPolicy) {
		this.memoryStoreEvictionPolicy = memoryStoreEvictionPolicy;
	}

	@Column(nullable = false, unique = true, length = 64)
	@Comment(value = "缓存名字", detailDesc = "用来识别不同的cache，必须惟一")
	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	@Column(nullable = false)
	@DefaultValue("0")
	@Comment(value = "硬盘缓存元素最大数", detailDesc = "硬盘管理的缓存元素数量最大限值。默认值为0，就是没有限制。")
	public int getMaxElementsOnDisk() {
		return maxElementsOnDisk;
	}

	public void setMaxElementsOnDisk(int maxElementsOnDisk) {
		this.maxElementsOnDisk = maxElementsOnDisk;
	}

	@Column(length = 1, nullable = false)
	@Comment(value = "是否进行磁盘存储", detailDesc = "设定在虚拟机重启时是否进行磁盘存储，默认为false.(一般对于安全小型应用，宜设为true)")
	@DefaultValue("1")
	public String getDiskPersistent() {
		return diskPersistent;
	}
	
	public boolean diskPersistent() {
		return "1".equals(diskPersistent);
	}

	public void setDiskPersistent(String diskPersistent) {
		this.diskPersistent = diskPersistent;
	}

	@Comment(value = "访问磁盘线程活动时间")
	public int getDiskExpiryThreadIntervalSeconds() {
		return diskExpiryThreadIntervalSeconds;
	}

	public void setDiskExpiryThreadIntervalSeconds(int diskExpiryThreadIntervalSeconds) {
		this.diskExpiryThreadIntervalSeconds = diskExpiryThreadIntervalSeconds;
	}

	@Column
	@DefaultValue("30")
	@Comment(value = "磁盘缓冲区大小", detailDesc = "存入磁盘时的缓冲区大小，默认30MB,每个缓存都有自己的缓冲区")
	public int getDiskSpoolBufferSizeMb() {
		return diskSpoolBufferSizeMb;
	}

	public void setDiskSpoolBufferSizeMb(int diskSpoolBufferSizeMb) {
		this.diskSpoolBufferSizeMb = diskSpoolBufferSizeMb;
	}

}
