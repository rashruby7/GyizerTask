package com.binarytree;

import java.util.Stack;

class MyTree
{
	MyTree leftnode,rightnode;

	@Override
	public String toString() {
		return "MyTree [leftnode=" + leftnode + ", rightnode=" + rightnode + "]";
	}
	
}
class LeafNode extends MyTree
{
	MyTree leftnode,rightNode;

	@Override
	public String toString() {
		return "LeafNode [leftnode=Z" + leftnode + ", rightnode=" + rightnode + "]";
	}
	
}

class Branch extends MyTree
{
	MyTree leftnode,rightnode;

	
	public Branch(MyTree leftnode, MyTree rightnode) {
		super();
		this.leftnode = leftnode;
		this.rightnode = rightnode;
	}


	@Override
	public String toString() {
		return "Branch [leftnode=" + leftnode + ", rightnode=" + rightnode + "]";
	}
	
	
}
public class EncodingDecoding {

	
	public static String treesToParenthesis(MyTree tree)
	{
		if(tree instanceof LeafNode) return "()";
		Branch branch=(Branch) tree;
		return "("+treesToParenthesis(branch.leftnode)+treesToParenthesis(branch.rightnode)+")";
		
	}
	public static MyTree parenthesisToTree(String parenthesis)
	{ 
		Stack<MyTree> stack=new Stack<MyTree>();
		Stack<Character> ch=new Stack<>();
		for(int i=0;i<parenthesis.length();i++)
		{
			if(parenthesis.charAt(i)=='(')
			{
				ch.push('(');
				
			}
			else{
				ch.pop();
				if(i!=parenthesis.length()-1)
				
					if(stack.size()>ch.size())
					{
						MyTree rightnode=stack.pop();
						MyTree leftnode=stack.pop();
						
						stack.push(new Branch(leftnode,rightnode));
					}
					else
					
						stack.push(new LeafNode());
					
				else
				{
					MyTree rightnode=stack.pop();
					MyTree leftnode =stack.pop();
					stack.push(new Branch(leftnode,rightnode));
			    	}
				 }
			}
			return stack.peek();
		}
	
		public static boolean isSameOrNot(MyTree root1, MyTree root2)
		{
			if(root1==null && root2==null) 
			
				return true;
			
			
			if(root1 !=null && root2 !=null)
			{
				return isSameOrNot(root1.leftnode,root2.leftnode) && isSameOrNot(root1.rightnode,root2.rightnode);
			}
			return false;
			
		}
	      public static void main(String[] args) {
			
		
	    	  MyTree tree=new Branch(new LeafNode(),new Branch(new LeafNode(),new LeafNode()));
	    	  String parenthesis =treesToParenthesis(tree);
	    	  System.out.println(parenthesis);
	    	  MyTree decodedTree=parenthesisToTree(parenthesis);
	    	  System.out.println(decodedTree.toString());
	    	  System.out.println(isSameOrNot(tree,decodedTree));
	    	  
	      }
	
	
}