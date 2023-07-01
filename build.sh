if [ ! -d ./out ]
then
    mkdir ./out
else
    rm -r ./out/*
fi
java -Xmx500M -cp "./antlr-4.13.0-complete.jar:$CLASSPATH" org.antlr.v4.Tool -package margarita Marg.g4 -o ./src 
javac -cp "./antlr-4.13.0-complete.jar:$CLASSPATH" $(find ./src| grep .java) -d ./out
