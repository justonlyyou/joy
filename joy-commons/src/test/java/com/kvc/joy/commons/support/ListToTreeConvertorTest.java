package com.kvc.joy.commons.support;

import com.kvc.joy.commons.bean.TreeNode;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 唐玮琳
 * @time 2013-4-2 下午11:17:23
 */
public class ListToTreeConvertorTest {


	/**
	 * Test method for {@link com.kvc.joy.commons.support.ListToTreeConvertor#convert(java.util.List)}.
	 */
	@Test
	public void testConvert() {
		List<TestRecord> list = new ArrayList<TestRecord>();
		list.add(new TestRecord("10", null, "根结点10"));
		list.add(new TestRecord("11", "10", "10的子结点11"));
		list.add(new TestRecord("12", "10", "10的子结点12"));
		list.add(new TestRecord("20", null, "根结点20"));
		list.add(new TestRecord("21", "20", "20的子结点21"));
		
		List<TreeNode<IListToTreeRestrict<String>>> treeList = ListToTreeConvertor.convert(list);
		
		boolean result = treeList.size() == 2;
		TreeNode<IListToTreeRestrict<String>> treeNode10 = treeList.get(0);
		result = result && "10".equals(treeNode10.getObject().getId());
		result = result && treeNode10.getChildren().size() == 2;
		TreeNode<IListToTreeRestrict<String>> treeNode11 = treeNode10.getChildren().get(0);
		result = result && "11".equals(treeNode11.getObject().getId());
		TreeNode<IListToTreeRestrict<String>> treeNode12 = treeNode10.getChildren().get(1);
		result = result && "12".equals(treeNode12.getObject().getId());
		TreeNode<IListToTreeRestrict<String>> treeNode20 = treeList.get(1);
		result = result && "20".equals(treeNode20.getObject().getId());
		result = result && treeNode20.getChildren().size() == 1;
		TreeNode<IListToTreeRestrict<String>> treeNode21 = treeNode20.getChildren().get(0);
		result = result && "21".equals(treeNode21.getObject().getId());
		Assert.assertTrue(result);
	}
	
	protected class TestRecord implements IListToTreeRestrict<String> {

		private static final long serialVersionUID = -3832151541461087421L;
		private String id;
		private String parentId;
		private String name;
		
		public TestRecord() {
		}
		
		public TestRecord(String id, String parentId, String name) {
			super();
			this.id = id;
			this.parentId = parentId;
			this.name = name;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String getParentId() {
			return parentId;
		}
		
		public String getName() {
			return name;
		}
		
	}

}
