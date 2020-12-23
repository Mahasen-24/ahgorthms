package manage.tree;

import java.util.ArrayList;

/**
 * in the tree we have some operations that will be implemented to make our tree 
 * self-balanced tree.(insert,delete,print,clear). 
 * */

public class RedBlackTree {

	// first of all we need a root which is the parent of all tree
	private Node root;
	private Node TNULL;
	private ArrayList<Integer> values = new ArrayList<>();
	/**
	 * @param node
	 * print values recursively
	 * to be used in preorder function below 
	 * */
	private void preOrderHelper(Node node) {
	    if (node != TNULL) {
	      System.out.print(node.value + " ");
	      preOrderHelper(node.leftChild);
	      preOrderHelper(node.rightChild);
	    }
	  }

	  /**
	   * @param node
	   * to be used in Inorder function below
	   * */ 
	  private void inOrderHelper(Node node) {
	    if (node != TNULL) {
	      inOrderHelper(node.leftChild);
	      System.out.print(node.value + " ");
	      inOrderHelper(node.rightChild);
	    }
	  }

	  /**
	   * @param node
	   * to be used in Postorder function below
	   * */ 
	  private void postOrderHelper(Node node) {
	    if (node != TNULL) {
	      postOrderHelper(node.leftChild);
	      postOrderHelper(node.rightChild);
	      System.out.print(node.value + " ");
	    }
	  }

	  /**
	   * @param node , value user's looking for
	   * to be used in Search function below
	   * */ 
	  private Node searchTreeHelper(Node node, int key) {
	    if (node == TNULL || key == node.value) {
	      return node;
	    }

	    if (key < node.value) {
	      return searchTreeHelper(node.leftChild, key);
	    }
	    return searchTreeHelper(node.rightChild, key);
	  }

	  // Balance the tree after deletion of a node
	  private void fixDelete(Node x) {
	    Node s;
	    while (x != root && x.color == 0) {
	      if (x == x.parent.leftChild) {
	        s = x.parent.rightChild;
	        if (s.color == 1) {
	          s.color = 0;
	          x.parent.color = 1;
	          leftRotate(x.parent);
	          s = x.parent.rightChild;
	        }

	        if (s.leftChild.color == 0 && s.rightChild.color == 0) {
	          s.color = 1;
	          x = x.parent;
	        } else {
	          if (s.rightChild.color == 0) {
	            s.leftChild.color = 0;
	            s.color = 1;
	            rightRotate(s);
	            s = x.parent.rightChild;
	          }

	          s.color = x.parent.color;
	          x.parent.color = 0;
	          s.rightChild.color = 0;
	          leftRotate(x.parent);
	          x = root;
	        }
	      } else {
	        s = x.parent.leftChild;
	        if (s.color == 1) {
	          s.color = 0;
	          x.parent.color = 1;
	          rightRotate(x.parent);
	          s = x.parent.leftChild;
	        }

	        if (s.rightChild.color == 0 && s.rightChild.color == 0) {
	          s.color = 1;
	          x = x.parent;
	        } else {
	          if (s.leftChild.color == 0) {
	            s.rightChild.color = 0;
	            s.color = 1;
	            leftRotate(s);
	            s = x.parent.leftChild;
	          }

	          s.color = x.parent.color;
	          x.parent.color = 0;
	          s.leftChild.color = 0;
	          rightRotate(x.parent);
	          x = root;
	        }
	      }
	    }
	    x.color = 0;
	  }

	  private void rbTransplant(Node u, Node v) {
	    if (u.parent == null) {
	      root = v;
	    } else if (u == u.parent.leftChild) {
	      u.parent.leftChild = v;
	    } else {
	      u.parent.rightChild = v;
	    }
	    v.parent = u.parent;
	  }

	  private void deleteNodeHelper(Node node, int key) {
	    Node z = TNULL;
	    Node x, y;
	    while (node != TNULL) {
	      if (node.value == key) {
	        z = node;
	      }

	      if (node.value <= key) {
	        node = node.rightChild;
	      } else {
	        node = node.leftChild;
	      }
	    }

	    if (z == TNULL) {
	      System.out.println("Couldn't find key in the tree");
	      return;
	    }

	    y = z;
	    int yOriginalColor = y.color;
	    if (z.leftChild == TNULL) {
	      x = z.rightChild;
	      rbTransplant(z, z.rightChild);
	    } else if (z.rightChild == TNULL) {
	      x = z.leftChild;
	      rbTransplant(z, z.leftChild);
	    } else {
	      y = minimum(z.rightChild);
	      yOriginalColor = y.color;
	      x = y.rightChild;
	      if (y.parent == z) {
	        x.parent = y;
	      } else {
	        rbTransplant(y, y.rightChild);
	        y.rightChild = z.rightChild;
	        y.rightChild.parent = y;
	      }

	      rbTransplant(z, y);
	      y.leftChild = z.leftChild;
	      y.leftChild.parent = y;
	      y.color = z.color;
	    }
	    if (yOriginalColor == 0) {
	      fixDelete(x);
	    }
	  }

	  // Balance the node after insertion
	  private void fixInsert(Node k) {
	    Node u;
	    while (k.parent.color == 1) {
	      if (k.parent == k.parent.parent.rightChild) {
	        u = k.parent.parent.leftChild;
	        if (u.color == 1) {
	          u.color = 0;
	          k.parent.color = 0;
	          k.parent.parent.color = 1;
	          k = k.parent.parent;
	        } else {
	          if (k == k.parent.leftChild) {
	            k = k.parent;
	            rightRotate(k);
	          }
	          k.parent.color = 0;
	          k.parent.parent.color = 1;
	          leftRotate(k.parent.parent);
	        }
	      } else {
	        u = k.parent.parent.rightChild;

	        if (u.color == 1) {
	          u.color = 0;
	          k.parent.color = 0;
	          k.parent.parent.color = 1;
	          k = k.parent.parent;
	        } else {
	          if (k == k.parent.rightChild) {
	            k = k.parent;
	            leftRotate(k);
	          }
	          k.parent.color = 0;
	          k.parent.parent.color = 1;
	          rightRotate(k.parent.parent);
	        }
	      }
	      if (k == root) {
	        break;
	      }
	    }
	    root.color = 0;
	  }

	  private void printHelper(Node root, String indent, boolean last) {
	    if (root != TNULL) {
	      System.out.print(indent);
	      if (last) {
	        System.out.print("R----");
	        indent += "   ";
	      } else {
	        System.out.print("L----");
	        indent += "|  ";
	      }

	      String sColor = root.color == 1 ? "RED" : "BLACK";
	      System.out.println(root.value + "(" + sColor + ")");
	      printHelper(root.leftChild, indent, false);
	      printHelper(root.rightChild, indent, true);
	    }
	  }

	  public RedBlackTree() {
	    TNULL = new Node();
	    TNULL.color = 0;
	    TNULL.leftChild = null;
	    TNULL.rightChild = null;
	    root = TNULL;
	  }

	  public void preorder() {
	    preOrderHelper(this.root);
	  }

	  public void inorder() {
	    inOrderHelper(this.root);
	  }

	  public void postorder() {
	    postOrderHelper(this.root);
	  }

	  public Node searchTree(int k) {
	    return searchTreeHelper(this.root, k);
	  }

	  public Node minimum(Node node) {
	    while (node.leftChild != TNULL) {
	      node = node.leftChild;
	    }
	    return node;
	  }

	  public Node maximum(Node node) {
	    while (node.rightChild != TNULL) {
	      node = node.rightChild;
	    }
	    return node;
	  }

	  public Node successor(Node node) {
	    if (node.rightChild != TNULL) {
	      return minimum(node.rightChild);
	    }

	    Node helper = node.parent;
	    while (helper != TNULL && node == helper.rightChild) {
	    	node = helper;
	    	helper= helper.parent;
	    }
	    return helper;
	  }
	  /**
	   * @param fHelper
	   * */
	  public Node predecessor(Node fHelper) {
	    if (fHelper.leftChild!= TNULL) {
	      return maximum(fHelper.leftChild);
	    }

	    Node sHelper = fHelper.parent;
	    while (sHelper != TNULL && fHelper == sHelper.leftChild) {
	    	fHelper = sHelper;
	    	sHelper = sHelper.parent;
	    }

	    return sHelper;
	  }

	  /**
	   * @param fHelper to help on rotating
	   * a function to rotate the tree.
	   * */
	  public void leftRotate(Node fHelper) {
	    Node sHelper = fHelper.rightChild;
	    fHelper.rightChild = sHelper.leftChild;
	    if (sHelper.leftChild != TNULL) {
	    	sHelper.leftChild.parent = fHelper;
	    }
	    sHelper.parent = fHelper.parent;
	    if (fHelper.parent == null) {
	      this.root = sHelper;
	    } else if (fHelper == fHelper.parent.leftChild) {
	    	fHelper.parent.leftChild = sHelper;
	    } else {
	    	fHelper.parent.rightChild = sHelper;
	    }
	    sHelper.leftChild = fHelper;
	    fHelper.parent = sHelper;
	  }

	  /**
	   * @param fHelper to help rotating tree
	   * a function to rotate the tree to right 
	   * */
	  public void rightRotate(Node fHelper) {
	    Node sHelper = fHelper.leftChild;
	    fHelper.leftChild = sHelper.rightChild;
	    if (sHelper.rightChild != TNULL) {
	      sHelper.rightChild.parent = fHelper;
	    }
	    sHelper.parent = fHelper.parent;
	    if (fHelper.parent == null) {
	      this.root = sHelper;
	    } else if (fHelper == fHelper.parent.rightChild) {
	      fHelper.parent.rightChild = sHelper;
	    } else {
	      fHelper.parent.leftChild = sHelper;
	    }
	    sHelper.rightChild = fHelper;
	    fHelper.parent = sHelper;
	  }
	
	  /**
	   * clear all tree with help of values array list. 
	   * */
	public void clear() {
		if (values.size()==0) {
			System.err.println("No values To remove.");
			return;
		}
		int i=0,value;
		while(!values.isEmpty()) {
			value=values.get(i);
			deleteNodeHelper(this.root,value);
			values.remove(i);
//			i++;
		}
		System.out.println("Tree cleared.");
	}

	/**
	 * print all tree
	 * */
	public void print() {
		printHelper(this.root,"",true);
	}

	/**
	 * @param value to be deleted
	 * to delete a specific node in the tree.
	 * */
	public void delete(int value) {
	    deleteNodeHelper(this.root, value);
	    if (values.contains(value)) {
	    	values.remove(values.indexOf(value));
	    }
	}
	
	/**
	 * @param value to be deleted
	 * to insert a specific node in the tree.
	 * */
	public void insert(int value) {
		
		Node node = new Node();
	    node.parent = null;
	    node.value = value;
	    node.leftChild = TNULL;
	    node.rightChild = TNULL;
	    node.color = 1; // to be red 
	    values.add(value);
	    Node y = null;
	    Node x = this.root;

	    while (x != TNULL) {
	      y = x;
	      if (node.value < x.value) {
	        x = x.leftChild;
	      } else {
	        x = x.rightChild;
	      }
	    }

	    node.parent = y;
	    if (y == null) {
	      root = node;
	    } else if (node.value < y.value) {
	      y.leftChild = node;
	    } else {
	      y.rightChild = node;
	    }

	    if (node.parent == null) {
	      node.color = 0;
	      return;
	    }

	    if (node.parent.parent == null) {
	      return;
	    }
	    fixInsert(node);
	  }
}

