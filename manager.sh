#!/bin/bash

MANAGER_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

cd $MANAGER_DIR
case $1 in
  compile)
    mvn clean package
    ;;
  test)
    mvn test
    ;;
  run)
    java -jar ./target/quicksort-computing-*-shaded.jar "${@:2}"
    ;;
  *)
    echo "Error: The command does not exist!!"
    exit 1
    ;;
esac
cd -
