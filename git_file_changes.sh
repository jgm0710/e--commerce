#!/bin/bash

echo_color() {
  echo -e "\033[35m[INFO]: ${1}\033[0m"
}


echo_color "-- Changed or Removed Files --"
changed=$(git diff --stat)
changed_list=$(echo "$changed" | awk 'NR>1{print buf}{buf = $0}')
changed_total=$(echo "$changed" | tail -n 1)
echo -e "$changed_list"

echo -e ""
echo_color "-- New Files --"
new=$(git diff --cached --stat)
new_list=$(echo "$new" | awk 'NR>1{print buf}{buf = $0}')
new_total=$(echo "$new" | tail -n 1)
echo -e "$new_list"

echo -e ""

echo_color "-- Total --"

echo "$changed_total"
echo "$new_total"
