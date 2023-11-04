spark-submit \
--master yarn \
--deploy-mode client \
--num-executors 2 \
--driver-memory 2g \
--executor-memory 2g \
--executor-cores 2 \
--class com.dh.SparkSqlDemo /home/ods/Spark4Job-1.0-SNAPSHOT.jar \  # 执行的jar包
 arg1 arg2        # jar包需要的参数