#!/bin/bash

n=20
P=1
for ((i=1;i<=n;i++))
do
	P=$((P*i))
done
echo ${P}
str='I am oldboy teacher welcome to oldboy training class'
for w in ${str}
do
	if [ ${#w} -le 6 ]
	then
		echo ${w}
	fi

done
