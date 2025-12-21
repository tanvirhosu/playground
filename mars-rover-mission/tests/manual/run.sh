#!/bin/bash

BASE_DIR="$(cd "$(dirname "$0")" && pwd)"
INDEX_PHP="$BASE_DIR/../../src/index.php"

# Colors for console output
green='\033[0;32m'
red='\033[0;31m'
reset='\033[0m'

echo "Running manual tests..."

for inputFile in "$BASE_DIR"/*.in; do
    testName=$(basename "$inputFile" .in)
    expectedFile="$BASE_DIR/$testName.out"
    
    if [ ! -f "$expectedFile" ]; then
        echo -e "${red}FAIL:${reset} Missing expected output file for $testName"
        continue
    fi

    actualOutput=$(php "$INDEX_PHP" < "$inputFile" 2>&1)
    expectedOutput=$(cat "$expectedFile")
    
    if [[ "$actualOutput" == *"$expectedOutput"* ]]; then
        echo -e "${green}PASS: $testName${reset}"
    else
        echo -e "${red}FAIL: $testName${reset}"
        echo "Expected to contain:"
        echo "$expectedOutput"
        echo "Actual output:"
        echo "$actualOutput"
    fi
done
