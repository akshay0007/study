The Java Class Loader is a part of the Java Runtime Environment that dynamically loads Java classes into the Java Virtual Machine.[1] Usually classes are only loaded on demand. The Java run time system does not need to know about files and file systems because of classloaders. Delegation is an important concept to understand when learning about classloaders.

Bootstrap class loader
Extensions class loader
System class loader
jvm is part of jre ,it calls main method 


class loaders==>jvm language classes
||
jvm memory
method area ||heap||java langauge stack ||pc register||netive method sstatck
||
execution engine=native method interface=native method library


class loader is reponsible for three activities::
loading
linking 
initalization

Loading : The Class loader reads the .class file, generate the corresponding binary data and save it in method area. For each .class file, JVM stores following information in method area.

Fully qualified name of the loaded class and its immediate parent class.
Whether .class file is related to Class or Interface or Enum
Modifier, Variables and Method information etc.


After loading .class file, JVM creates an object of type Class to represent this file in the heap memory. Please note that this object is of type Class predefined in java.lang package. This Class object can be used by the programmer for getting class level information like name of class, parent name, methods and variable information etc. To get this object reference we can use getClass() method of Object class.


lINKING::
Verification
Preparation
Resolution


Initialization::

Bootstrap class loader::JAVA_HOME/jre/lib
Extension class loader::JAVA_HOME/jre/lib/ext
System/Application class loader


Note : JVM follow Delegation-Hierarchy principle to load classes. System class loader delegate load request to extension class loader and extension class loader delegate request to boot-strap class loader. If class found in boot-strap path, class is loaded otherwise request again transfers to extension class loader and then to system class loader. At last if system class loader fails to load class, then we get run-time exception java.lang.ClassNotFoundException.


Method area :In method area, all class level information like class name, immediate parent class name, methods and variables information etc. are stored, including static variables. There is only one method area per JVM, and it is a shared resource.
Heap area :Information of all objects is stored in heap area. There is also one Heap Area per JVM. It is also a shared resource.

Stack area :For every thread, JVM create one run-time stack which is stored here. Every block of this stack is called activation record/stack frame which store methods calls. All local variables of that method are stored in their corresponding frame. After a thread terminate, it’s run-time stack will be destroyed by JVM. It is not a shared resource.

PC Registers :Store address of current execution instruction of a thread. Obviously each thread has separate PC Registers.

Native method stacks :For every thread, separate native stack is created. It stores native method information.





JAVA DEVELOPMENT KIT

The Java Development Kit (JDK) is a software development environment used for developing Java applications and applets. It includes the Java Runtime Environment (JRE), an interpreter/loader (Java), a compiler (javac), an archiver (jar), a documentation generator (Javadoc) and other tools needed in Java development.

JAVA RUNTIME ENVIRONMENT

JRE stands for “Java Runtime Environment” and may also be written as “Java RTE.” The Java Runtime Environment provides the minimum requirements for executing a Java application; it consists of the Java Virtual Machine (JVM), core classes, and supporting files.


JDK – Java Development Kit (in short JDK) is Kit which provides the environment to develop and execute(run) the Java program. JDK is a kit(or package) which includes two things
Development Tools(to provide an environment to develop your java programs)
JRE (to execute your java program).
Note : JDK is only used by Java Developers.

JRE – Java Runtime Environment (to say JRE) is an installation package which provides environment to only run(not develop) the java program(or application)onto your machine. JRE is only used by them who only wants to run the Java Programs i.e. end users of your system.
JVM – Java Virtual machine(JVM) is a very important part of both JDK and JRE because it is contained or inbuilt in both. Whatever Java program you run using JRE or JDK goes into JVM and JVM is responsible for executing the java program line by line hence it is also known as interpreter.


execution of java program::

Runtime
||
class loader
||
byte code verifier
||
interpreter
||
runtime
||
hardware


JVM Shutdown Hook in Java:::
Shutdown Hooks are a special construct that allow developers to plug in a piece of code to be executed when the JVM is shutting down. This comes in handy in cases where we need to do special clean up operations in case the VM is shutting down.
