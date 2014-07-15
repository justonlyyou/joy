package org.joy.swing.combobox;

//~--- JDK imports ------------------------------------------------------------
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import java.util.Set;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;

/**
 * Class description
 *
 *
 * @version    Enter version here..., 2008-11-12
 * @author     Enter your name here...
 */
class Filter implements Comparator {

    /** Field description */
    private String prefix;

    /**
     * Constructs ...
     *
     *
     * @param prefix
     */
    public Filter(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getMatch() {
        return prefix;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public boolean isEmptyMatch() {
        return (prefix == null) || prefix.isEmpty();
    }

    /**
     * Method description
     *
     *
     * @param matchString
     */
    public void setMatch(String matchString) {
        this.prefix = matchString;
    }

    /**
     * Method description
     *
     *
     * @param o
     *
     * @return
     */
    public boolean accept(Object o) {
        return o.toString().toLowerCase().contains(prefix.toLowerCase());
    }

    @Override
    public int compare(Object o1, Object o2) {
        String name = o1.toString();
        String anotherName = o2.toString();
        String lastKey = prefix;
        if ((lastKey == null) || lastKey.isEmpty()) {
            return name.compareTo(anotherName);
        }

        // 完全匹配
        if (lastKey.equals(name)) {
            return -1;
        }

        if (lastKey.equals(anotherName)) {
            return 1;
        }

        // 忽略的完全匹配
        if (lastKey.equalsIgnoreCase(name)) {
            return -1;
        }

        if (lastKey.equalsIgnoreCase(anotherName)) {
            return 1;
        }

        // 开始匹配
        if (name.startsWith(lastKey)) {
            return -1;
        }

        if (anotherName.startsWith(lastKey)) {
            return 1;
        }

        // 开始的忽略匹配
        if (name.toLowerCase().startsWith(lastKey.toLowerCase())) {
            return -1;
        }

        if (anotherName.toLowerCase().startsWith(lastKey.toLowerCase())) {
            return 1;
        }

        // 完全包含
        if (name.contains(lastKey)) {
            return -1;
        }

        if (anotherName.contains(lastKey)) {
            return 1;
        }

        // 忽略包含
        if (name.toLowerCase().contains(lastKey)) {
            return -1;
        }

        if (anotherName.toLowerCase().contains(lastKey)) {
            return 1;
        }

        return name.compareTo(anotherName);
    }
}

/**
 * Class description
 *
 *
 * @version    Enter version here..., 2008-11-12
 * @author     Enter your name here...
 */
public class FilterableComboBoxModel extends AbstractListModel implements MutableComboBoxModel, Comparator {

    /** Field description */
    private List items = new ArrayList();
    /** Field description */
    private List filteredItems = new ArrayList();
    /** Field description */
    private boolean filterable = true;    // 过滤开关
    /** Field description */
    private Filter filter;
    /** Field description */
    private Object selectedItem;
    /**
     * 自动过滤
     */
    private boolean autoSort = true;
    /**
     * 不需要排序的项
     */
    private Set unsortItems = null;

    /**
     * Constructs ...
     *
     */
    public FilterableComboBoxModel() {
    }

    /**
     * Constructs ...
     *
     *
     * @param items
     */
    public FilterableComboBoxModel(List items) {
        setItems(items);
    }

    public FilterableComboBoxModel(boolean autoSort) {
        setAutoSort(autoSort);
    }

    public FilterableComboBoxModel(List items, boolean autoSort) {
        setAutoSort(autoSort);
        setItems(items);
    }

    private void sort() {
        if(autoSort){
            Collections.sort(items, this);
        }
    }

    /**
     * Method description
     *
     *
     * @param items
     */
    public void setItems(Collection items) {
        this.items = new ArrayList(items);
        sort();
        filteredItems = new ArrayList(items.size());
        updateFilteredItems();
    }

    public void addItems(Collection items) {
        this.items.addAll(items);
        sort();
        updateFilteredItems();
    }

    /**
     * 获得Model里的数据
     *
     * @return
     */
    public List getAllData() {
        return items;
    }

    /**
     * Method description
     *
     *
     * @param obj
     */
    public void addElement(Object obj) {
        items.add(obj);
        sort();
        updateFilteredItems();
    }

    /**
     * Method description
     *
     *
     * @param obj
     */
    public void removeElement(Object obj) {
        items.remove(obj);
        updateFilteredItems();
    }

    /**
     * Method description
     *
     */
    public void removeElements() {
        this.items.clear();
        this.filteredItems.clear();
        this.selectedItem = null;
        this.updateFilteredItems();
    }

    /**
     * Method description
     *
     *
     * @param index
     */
    public void removeElementAt(int index) {
        items.remove(index);
        updateFilteredItems();
    }

    /**
     * Method description
     *
     *
     * @param obj
     * @param index
     */
    public void insertElementAt(Object obj, int index) {
        items.add(index, obj);
        sort();
        updateFilteredItems();
    }

    /**
     * Method description
     *
     *
     * @param filter
     */
    public void setFilter(Filter filter) {
        this.filter = filter;
        updateFilteredItems();
    }

    /**
     * Method description
     *
     *
     * @param matchString
     */
    public void setFilterMatch(String matchString) {
        if (this.filter == null) {
            filter = new Filter(matchString);
        } else {
            filter.setMatch(matchString);
        }

        updateFilteredItems();
    }

    /**
     * Method description
     *
     */
    protected void updateFilteredItems() {
        fireIntervalRemoved(this, 0, filteredItems.size());
        filteredItems.clear();

        if (!filterable || (filter == null) || filter.isEmptyMatch()) {
            filteredItems.addAll(items);
        } else {
            for (Iterator iterator = items.iterator(); iterator.hasNext();) {
                Object item = iterator.next();

//                if (filterable) {
                if (filter.accept(item)) {
                    filteredItems.add(item);
                }
//                } else {
//                    filteredItems.add(item);
//                }
            }
            Collections.sort(filteredItems, filter);
        }

        fireIntervalAdded(this, 0, filteredItems.size());
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public int getSize() {
        return filteredItems.size();
    }

    /**
     * Method description
     *
     *
     * @param index
     *
     * @return
     */
    public Object getElementAt(int index) {
        return filteredItems.get(index);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Object getSelectedItem() {
        return selectedItem;
    }

    /**
     * Method description
     *
     *
     * @param val
     */
    public void setSelectedItem(Object val) {
        if ((selectedItem == null) && (val == null)) {
            return;
        }

        if ((selectedItem != null) && selectedItem.equals(val)) {
            return;
        }

        if ((val != null) && val.equals(selectedItem)) {
            return;
        }

        selectedItem = val;
        fireContentsChanged(this, -1, -1);
    }

    /**
     * Method description
     *
     *
     * @param filterable
     */
    public void setFilterable(boolean filterable) {
        this.filterable = filterable;
    }

    @Override
    public int compare(Object o1, Object o2) {
        if (o1 == o2) {
            return 0;
        }
        if(unsortItems != null && (unsortItems.contains(o1) || unsortItems.contains(o2))  ){
            return 0;
        }
        if (o1 == null || o2 == null) {
            return o1 == null ? -1 : 1;
        }
        return o1.toString().compareTo(o2.toString());
    }

    /**
     * @return the autoSort
     */
    public boolean isAutoSort() {
        return autoSort;
    }

    /**
     * @param autoSort the autoSort to set
     */
    public void setAutoSort(boolean autoSort) {
        this.autoSort = autoSort;
    }

    /**
     * @return the unsortItems
     */
    public Set getUnsortItems() {
        return unsortItems;
    }

    /**
     * @param unsortItems the unsortItems to set
     */
    public void setUnsortItems(Collection unsortItems) {
        if(this.unsortItems == null){
            this.unsortItems = new HashSet();
        }
        this.unsortItems.addAll(unsortItems);
    }
    public void setUnsortItems(Object... unsortItems) {
        if(this.unsortItems == null){
            this.unsortItems = new HashSet();
        }
        for(Object unSortItem : unsortItems){
             this.unsortItems.add(unSortItem);
        }
    }
}
