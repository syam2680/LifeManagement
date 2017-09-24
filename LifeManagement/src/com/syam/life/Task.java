package com.syam.life;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import lombok.Data;

@Data
@Root
public class Task implements Serializable {
	private static final long serialVersionUID = 6362746553177791212L;
	private static AtomicInteger ID = new AtomicInteger(0);
	@Element
	private int taskID;
	@Element
	private String taskName;
	@Element
	private boolean isCompleted;
	@Element
	private Date startDate;
	@Element
	private Date dueDate;
	@Element
	private Date endDate;
	@Element
	private int priority;
	@Element
	private String category;
	private static TreeMap<Integer, Task> database = new TreeMap<Integer, Task>();
	//@Element
	//private List<Task> subtask;
	static {		
		File file = new File("/Users/syamk/Desktop/LifeManagement/xml");
		
		for (File source : file.listFiles()) {
			System.out.println(source.getName());
			try {
				Serializer serializer = new Persister();
				Task task = serializer.read(Task.class, source);
				database.put(task.getTaskID(), task);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (file.listFiles().length > 0) {
			ID = new AtomicInteger(database.lastKey());
		}
	}

	public Task(String taskName, Date startDate, Date dueDate,int priority,String category) {
		this.taskName = taskName;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.isCompleted = false;
		this.endDate = dueDate;
		this.taskID = ID.incrementAndGet();
		this.priority=priority;
		this.category=category;
		//this.subtask=new ArrayList<Task>();
		database.put(taskID, this);
		this.save();
	}

	public void completeTask(int taskID) {
		Task task = database.get(taskID);
		task.isCompleted = true;
		task.endDate = new Date();
		task.save();
	}

	public static void completeTask(int taskID, Date endDate) {
		Task task = database.get(taskID);
		task.isCompleted = true;
		task.endDate = endDate;
		task.save();
	}

	public void save() {
		try {
			Serializer serializer = new Persister();
			File result = new File(WD + this.getTaskID() + ".xml");
			result.createNewFile();
			serializer.write(this, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		File result = new File(WD + this.getTaskID() + ".xml");
		if (database.containsKey(this.getTaskID()))
			database.remove(this.getTaskID());
		if (result.exists())
			result.delete();
	}

	public static void delete(int id) {
		File result = new File(WD + id + ".xml");
		if (database.containsKey(id))
			database.remove(id);

		if (result.exists())
			result.delete();
	}
	
	
	public static TreeMap<Integer, Task> getDB()
	{
		return database;
	}
	
	

	private Task() {

	}

	private static String WD="/Users/syamk/Desktop/LifeManagement/xml/";
}
