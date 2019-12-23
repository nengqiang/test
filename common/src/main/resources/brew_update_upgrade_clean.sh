#!/bin/sh
  echo "brew update..."
  brew update

  echo "brew upgrade..."
  brew upgrade

  echo "brew cleanup..."
  brew cleanup

  caskapp=`brew cask list`

  for app in $caskapp
  do
    echo "try upgrae" ${app}
    brew cask upgrade $app
  done