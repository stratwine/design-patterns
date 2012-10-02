package com.patterns.chain;

/*
   This implementation contains one method that must be overridden.
   The other method acts as the chain invoker.
 */
abstract class Logger {
	
	protected Logger nextLogger;
	
	public void setNextLogger(Logger logger)
	{
		this.nextLogger=logger;
	}


    /*
        chain behavior is established by
        1) invoking the processor method. Here writeMessage() is the processor method.
        2) invoking the next one in the chain
     */
	public void message(String message, int priority)
	{
		this.writeMessage(message, priority);
		if(nextLogger!=null)
		{
			nextLogger.message(message, priority);
		}
	}
	
	abstract protected void writeMessage(String message,int priority);
	

}

class EmailLogger extends Logger
{
	@Override
	public void writeMessage(String message,int priority)
	{
		if(priority==3){
		System.out.println("Alerting by Email: "+message);
		}
	}
}

class ConsoleLogger extends Logger
{
	@Override
	public void writeMessage(String message,int priority)
	{
		if(priority<=3)
		{
		 System.out.println("Logging onto console: "+message);
		}
	}
}

class StdErrLogger extends Logger
{
	@Override
	public void writeMessage(String message,int priority)
	{
		if(priority>=2)
		{
			System.out.println("Logging on to StdErr: "+message);
		}
	}
}

public class ChainOfResponsibility
{
	public static void main(String[] args)
	{
        //creation of chain
        // ConsoleHandler-->StdErrHandler
		Logger emailLogger = new EmailLogger();
        Logger consoleLogger = new ConsoleLogger();
        consoleLogger.setNextLogger(new StdErrLogger());
        emailLogger.setNextLogger(consoleLogger);
        emailLogger.message("check", 3);

	}
}
