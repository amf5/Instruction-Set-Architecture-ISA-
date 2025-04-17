/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package architecture;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import com.sun.org.apache.bcel.internal.generic.InstructionComparator;
import com.sun.xml.internal.fastinfoset.tools.StAX2SAXReader;
import java.util.ArrayList;
import java.util.Stack;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author ACER
 */
class project {
public int priority (char c){
    switch (c) {
        case '^':
            return 3;
        case '*':
        case '/':
            return 2;
        case '+':
        case '-':
            return 1;
        default:
            return 0;
    }
}
public ArrayList<String> post ( String infix){
int i=0;
ArrayList<String> post=new ArrayList<>();
Stack <Character> stack= new Stack();

while (i!= infix.length())
{
   char c=infix.charAt(i++);
   
    if(Character.isLetterOrDigit(c)){
   String e=""+c;
char next=infix.charAt(i);
        while(Character.isLetterOrDigit(next))
        {
        e+=next;
        next=infix.charAt(++i);
        }
        post.add(e);
    }
    else{
switch (c) {
    case '^':
        stack.push(c);
        break;
    case '+':
    case '-':
    case '/':
    case '*':
while(!stack.empty()&&priority(c)<= priority(stack.peek()))
{
post.add(String.valueOf(stack.pop()));
}
stack.push(c);
break;
    case '(':
        stack.push(c);
break;
    case')':
       

       while( !stack.empty()&&stack.peek() != '('){
                  post.add(String.valueOf(stack.pop()));
           
       }
stack.pop();
        break;
default:
break;

}

    } }
    
    while(!stack.empty()){
    
     post.add(String.valueOf(stack.pop()));
    
    }
    
    return post;
    
    

}

public String meaningofoperator(char c){
switch(c){
    case'+':
return "ADD";
case'-':
 return "Abstract";
case '*':
    return "multiy";
case '/':
    return "division";
case '^':
    return "power";
   
default:
    return "";
}}
public String[] Structure(ArrayList<String> post)
{
int i=0,r=0,t=0;

String three="",register="",register2="",two="",one="",zero="";
String [] arr=new String[3];
Stack<String> var=new Stack<>();
Stack<String> var2=new Stack<>();
while(i<post.size())
{
String e=post.get(i++);
if(e.length()>1||Character.isLetterOrDigit(e.charAt(0)))
{
var.push(e);
var2.push(e);
t++;
}else{
   String data2="";
  String data1="";
    if(!var.empty()){
   data2=var.pop();
   data1=var.pop();
zero+="push"+"    "+data1+"\n"+"push"+"    "+data2+"\n"+meaningofoperator(e.charAt(0))+"\n";
 
    
    }
    
   String data4=var2.pop();
 String data3=var2.pop();
register="R"+ ++r;
if(var2.empty()){register2="Z";}
else{
register2=register;
}
var2.push(register);
if(var.empty()&&data2!=data4&&data1!=data3){
data2+="l"+5;
data1+="l"+5;
    zero+=meaningofoperator(e.charAt(0))+"\n";

}
two+="load"+"  "+register+" , "+data3+"\n"+meaningofoperator(e.charAt(0))+"   "+register+" , "+data4+"\n";

three+=meaningofoperator(e.charAt(0))+"    "+register2+"   "+data3+" , "+data4+"\n";




}
}

two+="Store"+"    "+"z";
zero+="Store"+"    "+"z";
arr[0]=three;
arr[1]=two;
arr[2]=zero;
return arr;
}


}
public class Architecture {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        project r=new project();
            ArrayList<String> e=r.post("(Wdd+r)*(u+p)");
    for(int i=0; i<e.size();i++)
    {
        System.out.println(e.get(i));
    
    }
        String[] a=r.Structure(r.post("(w-r)+(t+u)"));
        System.out.println("The three Address:");
        System.out.println(a[0]);
        System.out.println("\n");
        System.out.println("the two Address:");
        System.out.println(a[1]);
        System.out.println("\n");
        System.out.println("The Zero Address:");
        System.out.println(a[2]);
    
    
        }
    
}
