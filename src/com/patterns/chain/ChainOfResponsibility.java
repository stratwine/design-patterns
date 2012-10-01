package com.patterns.chain;

abstract class Logger {
	
	protected Logger nextLogger;
	
	public void setNextLogger(Logger logger)
	{
		this.nextLogger=logger;
	}
	
	public void message(String message, int priority)
	{
		this.writeMessage(message, priority);
		if(nextLogger!=null)
		{
			nextLogger.writeMessage(message, priority);
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
		System.out.println("Altering by Email: "+message);
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
		Logger logger = new ConsoleLogger();
		logger.setNextLogger(new StdErrLogger());
		logger.message("check", 3);
	}
}
