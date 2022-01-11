.class public Main
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 128
.limit locals 128
		new Main
		invokespecial Main/<init>()V
		return
.end method
.method public <init>()V
.limit stack 128
.limit locals 128
		aload 0
		invokespecial java/lang/Object/<init>()V
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 1
		aload 1
		ldc 23
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		
		new Fptr
		dup
		aload 0
		ldc "foo"
		invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V
		aload 1
		invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;
		pop
		return
.end method
		 
.method public bar(Ljava/lang/Integer;)V
.limit stack 128
.limit locals 128
		getstatic java/lang/System/out Ljava/io/PrintStream;
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		invokevirtual java/io/PrintStream/println(I)V
		return
.end method
		 
.method public foo(Ljava/lang/Integer;)V
.limit stack 128
.limit locals 128
		getstatic java/lang/System/out Ljava/io/PrintStream;
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		invokevirtual java/io/PrintStream/println(I)V
		new A
		dup
		invokespecial A/<init>()V
		astore 3
		aload 5
		
		new Fptr
		dup
		aload 0
		ldc "bar"
		invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V
		dup_x1
		putfield A/a LFptr;
		pop
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 6
		aload 6
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 7
		getfield A/a LFptr;
		aload 6
		invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;
		pop
		return
.end method
		 
