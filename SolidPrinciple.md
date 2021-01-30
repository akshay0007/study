solid principle

1-Single responsiblity-- classs should have only one reason to change

incorrect--
example--
public class Task{
 downloadFile
 parseTheFile
 PersistData
}
	
2-Open and close-
 s/w is open for extension but cls for modification
 
 incorrect--
 example --
 public class Imple{
 void Area(Object[] ar){
	if(ar typeof Rectangle){
	}else {
	
	}
 }
 }
 
 public abstarct class Shape{
	abstarct int getArea();
 }
 
 class Rectange: Shape{
	getArea
 }
	
3-Liskov princple-(substituation principle)
 We can use inheirtace when super class is replacable by subclass 
 incorrect 
 example--
 public class Rectange{
	void setWight
	void setHeight
}
class Square{
	void setWight
	void setHeight
	}
	
if we are validating area of square and passing w and h diff getting wrong answer 
bcoz this is not fully replaceable by reactangle	
 
4-Interface segregation--
 if 2 class calling to each other 
 it means we are not forcing any clinet to implement all methods of interface if it isn't requeired
 
example--
 interface Animal
	void feed
  interface Pet extends  Animal
    void feed
	void groom
	
public class Lion extends Animal{}
public class Dog extends Pet{}

5- Dependency inversion -- depands on interface and abstarct rather then on concreate classes
	
	
	exaple-- worng pattern 
	enum OutputDevice{
		Printer,Disk
	}
	
	void copy(OutputDevice device){
		if(device == Printer){
			write
		}else{
			read
		}
	}
	correct
	
	interface Reader
		read()
		
	interface Writer
		write(char cha)
		
	copy(Reader r,Writer w){
	  w.write();
	}
