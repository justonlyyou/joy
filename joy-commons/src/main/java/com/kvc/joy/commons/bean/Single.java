package com.kvc.joy.commons.bean;

/**
 * <p>
 * 单个属性的对象
 * 应用场景如：想从内部类带回基本类型的值
 * </p>
 * 
 * <pre>
 * final Single<String> single = new Single<String>();
 * new ICommand() {
 * 		
 * 		@Override
 * 		public void execute() {
 * 			String str = "str";
 * 			single.setValue(str);
 * 		}
 * 	};
 * 	String value = single.getValue();
 * </pre>
 * 
 * @since 1.0.0
 * @author <b>唐玮琳</b>
 */
public class Single<T> {

	private T value;

	public Single() {
	}

	public Single(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		if (value == null) {
			return "";
		}
		return value.toString();
	}

}
