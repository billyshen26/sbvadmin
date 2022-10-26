#!/usr/bin/env bash
echo 'update frontend'
git submodule init
git submodule update
cd sbvadmin-vben
git pull origin main
yarn --version