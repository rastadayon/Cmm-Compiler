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
		invokespecial java/lang/Object/<init>()V
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 1
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 2
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<Object>()V
		astore 10
		astore 3
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<Object>()V
		astore 11
		astore 4
		aconst_null
		astore 5
		ldc 0
		dup
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 1
		pop
		aload 3
		ldc 0
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast ProductCatalog
		ldc 5000
		dup_x1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		putfield ProductCatalog/price Ljava/lang/Integer;
		pop
		aload 3
		ldc 1
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast ProductCatalog
		ldc 4000
		dup_x1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		putfield ProductCatalog/price Ljava/lang/Integer;
		pop
		aload 3
		ldc 2
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast ProductCatalog
		ldc 2000
		dup_x1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		putfield ProductCatalog/price Ljava/lang/Integer;
		pop
		aload 3
		ldc 3
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast ProductCatalog
		ldc 8000
		dup_x1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		putfield ProductCatalog/price Ljava/lang/Integer;
		pop
		ldc 0
		dup
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 1
		pop
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 12
		aload 12
		aload 4
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		
		aload 12
		invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;
		
		checkcast java/lang/Integer
		invokevirtual java/lang/Integer/intValue()I
		dup
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 2
		pop
		getstatic java/lang/System/out Ljava/io/PrintStream;
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		invokevirtual java/io/PrintStream/println(I)V
		return
.end method
		 
.method public createOrder(LProductCatalog;Ljava/lang/Integer;)LOrder;
.limit stack 128
.limit locals 128
		aconst_null
		astore 3
		aload 3
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		dup_x1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		putfield Order/quantity Ljava/lang/Integer;
		pop
		aload 3
		aload 1
		dup_x1
		putfield Order/product LProductCatalog;
		pop
		aload 3
		areturn
.end method
		 
.method public getSum(LList;)Ljava/lang/Integer;
.limit stack 128
.limit locals 128
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 2
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 3
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		astore 4
		aload 3
		invokevirtual java/lang/Integer/intValue()I
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		areturn
.end method
		 
