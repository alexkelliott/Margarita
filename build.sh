if [ ! -d ./out ]
then
    mkdir ./out
else
    rm ./out/*
fi
java -Xmx500M -cp "./antlr-4.13.0-complete.jar:$CLASSPATH" org.antlr.v4.Tool Lang.g4 -o ./src
javac -cp "./antlr-4.13.0-complete.jar:$CLASSPATH" src/*.java -d ./out
