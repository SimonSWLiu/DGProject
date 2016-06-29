package com.study.designpattern.singleton;

/**
 * 五、 双重校验锁，在当前的内存模型中无效 我们来看看这个场景：假设线程一执行到instance = new
 * LockSingleton()这句，这里看起来是一句话
 * ，但实际上它并不是一个原子操作（原子操作的意思就是这条语句要么就被执行完，要么就没有被执行过，不能出现执行了一半这种情形
 * ）。事实上高级语言里面非原子操作有很多，我们只要看看这句话被编译后在JVM执行的对应汇编代码就发现，这句话被编译成8条汇编指令，大致做了3件事情：
 * 1.给LockSingleton的实例分配内存。 2.初始化LockSingleton的构造器
 * 3.将instance对象指向分配的内存空间（注意到这步instance就非null了）。
 * 但是，由于Java编译器允许处理器乱序执行（out-of-order），以及JDK1.5之前JMM（Java Memory
 * Medel）中Cache、寄存器到主内存回写顺序的规定
 * ，上面的第二点和第三点的顺序是无法保证的，也就是说，执行顺序可能是1-2-3也可能是1-3-2，如果是后者
 * ，并且在3执行完毕、2未执行之前，被切换到线程二上
 * ，这时候instance因为已经在线程一内执行过了第三点，instance已经是非空了，所以线程二直接拿走instance
 * ，然后使用，然后顺理成章地报错，而且这种难以跟踪难以重现的错误估计调试上一星期都未必能找得出来，真是一茶几的杯具啊。
 */
class LockSingleton {
	private volatile static LockSingleton singleton;

	private LockSingleton() {
	}

	// 详见：http://www.ibm.com/developerworks/cn/java/j-dcl.html
	public static LockSingleton getInstance() {
		if (singleton == null) {
			synchronized (LockSingleton.class) {
				if (singleton == null) {
					singleton = new LockSingleton();
				}
			}
		}
		return singleton;
	}

}
