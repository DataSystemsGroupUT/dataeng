# Create Images Command Line
#cli #programming #utilities

The command-line convert utility in the *[ImageMagick](https://www.imagemagick.org/Usage/)* suite can easily create an image of specified dimensions:

```convert -size 200x200 xc:white canvas.png```

To create an image with text, use additional options:

```convert -size 200x200 -gravity center -background white -fill black \
        label:"text goes here" canvas.png```