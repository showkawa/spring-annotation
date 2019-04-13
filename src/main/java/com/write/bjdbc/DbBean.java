package com.write.bjdbc;

public  class  DbBean {

	protected static final String driverName = "com.mysql.jdbc.Driver";

	protected static String url = "jdbc:mysql://remotemysql.com:3306/khgvUiO4eh";

	protected static String userName = "khgvUiO4eh";

	protected static String password = "BGAAee478r";

	protected static String poolName = "brian1991";// 连接池名字

	protected static int minConnections = 1; // 空闲池，最小连接数

	protected static int maxConnections = 10; // 空闲池，最大连接数

	protected static int initConnections = 5;// 初始化连接数

	protected static long connTimeOut = 1000;// 重复获得连接的频率

	protected static int maxActiveConnections = 100;// 最大允许的连接数，和数据库对应

	protected static long connectionTimeOut = 1000 * 60 * 20;// 连接超时时间，默认20分钟


}
