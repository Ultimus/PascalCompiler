.bytecode 50.0
.class public simpleExample
.super java/lang/Object
.method public <init>()V
	limit stack 1
	.limit locals 1
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method
	.method public static main([Ljava/lang/String;)V
	.limit stack 3
	.limit locals 2
	bipush 1
	getstatic java/lang/System/out Ljava/io/PrintStream; 
	invokevirtual java/io/PrintStream/println()V
	return
.end method