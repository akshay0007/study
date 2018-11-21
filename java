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



1. Shutdown Hooks may not be executed in some cases!
2. Once started, Shutdown Hooks can be forcibly stopped before completion.
3. We can have more than one Shutdown Hooks, but their execution order is not guaranteed.
4. We cannot register / unregister Shutdown Hooks with in Shutdown Hooks
5. Once shutdown sequence starts, it can be stopped by Runtime.halt() only.
6. Using shutdown hooks require security permissions.


Main difference between .equals() method and == operator is that one is method and other is operator.
We can use == operators for reference comparison (address comparison) and .equals() method for content comparison. In simple words, == checks if both objects point to the same memory location whereas .equals() evaluates to the comparison of values in the objects.
If a class does not override the equals method, then by default it uses equals(Object o) method of the closest parent class that has overridden this method. See this for detail


Threads producerss::
Two types of thread in java::
deamon thread::run in background low prorities threads
user thread::higer prority thread run in foreground


User Thread or Non-Daemon are designed to do specific or complex task where as daemon threads are used to perform supporting tasks.

JVM doesn’t wait for daemon thread to finish but it waits for User Thread 

Thread Priority : The User threads are high priority as compare to daemon thread means they won’t get CPU as easily as a user thread can get.


Creation of Thread : User thread is usually created by the application for executing some task concurrently. On the other hand, daemon thread is mostly created by JVM like for some garbage collection job.


Termination of Thread : JVM will force daemon thread to terminate if all user threads have finished their execution but The user thread is closed by application or by itself. A user thread can keep running by the JVM running but a daemon thread cannot keep running by the JVM. This is the most critical difference between user thread and daemon thread.


Usage : The daemons threads are not used for any critical task. Any important task is done by user thread. A daemon thread is generally used for some background tasks which are not critical task.


User threads are created by the application.

// Java program check thread is Daemon or not 


//producer and consumer problems:::

In computing, the producer–consumer problem (also known as the bounded-buffer problem) is a classic example of a multi-process synchronization problem. The problem describes two processes, the producer and the consumer, which share a common, fixed-size buffer used as a queue.

The producer’s job is to generate data, put it into the buffer, and start again.
At the same time, the consumer is consuming the data (i.e. removing it from the buffer), one piece at a time.



Implementation of Producer Consumer Class:::

A LinkedList list – to store list of jobs in queue.
A Variable Capacity – to check for if the list is full or not
A mechanism to control the insertion and extraction from this list so that we do not insert into list if it is full or remove from it if it is empty.


public class MainClass {

    public static void main(String arg[]) {

        final PC obj = new PC();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    obj.produces();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try 
}

class ThreadJoining extends Thread {
    String name;

    public ThreadJoining(String name) {
        this.name = name;
    }

    public void run() {
        try {
            for (int i = 0; i < 2; i++) {
                Thread.sleep(1000);
                System.out.println("call here " + name);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}


Thread Pools in Java:::
A server that creates a new thread for every request would spend more time and consume more system resources in creating and destroying threads than processing actual requests.

A thread pool reuses previously created threads to execute current tasks and offers a solution to the problem of thread cycle overhead and resource thrashing.

Since active threads consume system resources, a JVM creating too many threads at the same time can cause the system to run out of memory. This necessitates the need to limit the number of threads being created.


ava provides the Executor framework which is centered around the Executor interface, its sub-interface –ExecutorService and the class-ThreadPoolExecutor, which implements both of these interfaces. By using the executor, one only has to implement the Runnable objects and send them to the executor to execute.


newFixedThreadPool(int)           Creates a fixed size thread pool.
newCachedThreadPool()             Creates a thread pool that creates new 
                                  threads as needed, but will reuse previously 
                                  constructed threads when they are available
newSingleThreadExecutor()         Creates a single thread.

{
                    obj.consume();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        // t1 finishes before t2
//        t1.join();
//        t2.join();

    }

}


class PC {
    LinkedList<Integer> list = new LinkedList<>();
    int capcity = 10;

    public void produces() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                while (list.size() == capcity)
                    wait();
                Syst
}

class ThreadJoining extends Thread {
    String name;

    public ThreadJoining(String name) {
        this.name = name;
    }

    public void run() {
        try {
            for (int i = 0; i < 2; i++) {
                Thread.sleep(1000);
                System.out.println("call here " + name);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}


Thread Pools in Java:::
A server that creates a new thread for every request would spend more time and consume more system resources in creating and destroying threads than processing actual requests.

A thread pool reuses previously created threads to execute current tasks and offers a solution to the problem of thread cycle overhead and resource thrashing.

Since active threads consume system resources, a JVM creating too many threads at the same time can cause the system to run out of memory. This necessitates the need to limit the number of threads being created.


ava provides the Executor framework which is centered around the Executor interface, its sub-interface –ExecutorService and the class-ThreadPoolExecutor, which implements both of these interfaces. By using the executor, one only has to implement the Runnable objects and send them to the executor to execute.


newFixedThreadPool(int)           Creates a fixed size thread pool.
newCachedThreadPool()             Creates a thread pool that creates new 
                                  threads as needed, but will reuse previously 
                                  constructed threads when they are available
newSingleThreadExecutor()         Creates a single thread.

em.out.println("produces produce value" + value);
                list.add(value++);
                notify();
                Thread.sleep(1000);
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (list.size() == 0)
                    wait();

                Integer val = (Integer) list.removeFirst();
                notify();
                Thread.sleep(1000);
            }
        }
    }


}

class ThreadJoining extends Thread {
    String name;

    public ThreadJoining(String name) {
        this.name = name;
    }

    public void run() {
        try {
            for (int i = 0; i < 2; i++) {
                Thread.sleep(1000);
                System.out.println("call here " + name);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}


Thread Pools in Java:::
A server that creates a new thread for every request would spend more time and consume more system resources in creating and destroying threads than processing actual requests.

A thread pool reuses previously created threads to execute current tasks and offers a solution to the problem of thread cycle overhead and resource thrashing.

Since active threads consume system resources, a JVM creating too many threads at the same time can cause the system to run out of memory. This necessitates the need to limit the number of threads being created.


ava provides the Executor framework which is centered around the Executor interface, its sub-interface –ExecutorService and the class-ThreadPoolExecutor, which implements both of these interfaces. By using the executor, one only has to implement the Runnable objects and send them to the executor to execute.


newFixedThreadPool(int)           Creates a fixed size thread pool.
newCachedThreadPool()             Creates a thread pool that creates new 
                                  threads as needed, but will reuse previously 
                                  constructed threads when they are available
newSingleThreadExecutor()         Creates a single thread.


 





