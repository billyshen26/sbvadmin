#!/usr/bin/env bash
echo 'update frontend'
git submodule init
cd sbvadmin-vben
git pull origin main
yarn --version