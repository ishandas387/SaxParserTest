package com.sax.test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CustomTreeNode<T> implements Iterable<CustomTreeNode<T>> {

	public T data;
	public CustomTreeNode<T> parent;
	public List<CustomTreeNode<T>> listOfChildren;

	public boolean isRoot() {
		return parent == null;
	}

	public boolean isLeaf() {
		return listOfChildren.size() == 0;
	}

	private List<CustomTreeNode<T>> elementsIndex;

	public CustomTreeNode(T data) {
		this.data = data;
		this.listOfChildren = new LinkedList<CustomTreeNode<T>>();
		this.elementsIndex = new LinkedList<CustomTreeNode<T>>();
		this.elementsIndex.add(this);
	}

	public CustomTreeNode<T> addChild(T child) {
		CustomTreeNode<T> childNode = new CustomTreeNode<T>(child);
		childNode.parent = this;
		this.listOfChildren.add(childNode);
		this.registerChildForSearch(childNode);
		return childNode;
	}

	public int getLevel() {
		if (this.isRoot())
			return 0;
		else
			return parent.getLevel() + 1;
	}

	private void registerChildForSearch(CustomTreeNode<T> node) {
		elementsIndex.add(node);
		if (parent != null)
			parent.registerChildForSearch(node);
	}

	public CustomTreeNode<T> findTreeNode(Comparable<T> cmp) {
		for (CustomTreeNode<T> element : this.elementsIndex) {
			T elData = element.data;
			if (cmp.compareTo(elData) == 0)
				return element;
		}

		return null;
	}

	@Override
	public String toString() {
		return data != null ? data.toString() : "[data null]";
	}

	@Override
	public Iterator<CustomTreeNode<T>> iterator() {
		CustomTreeNodeIter<T> iter = new CustomTreeNodeIter<T>(this);
		return iter;
	}

}