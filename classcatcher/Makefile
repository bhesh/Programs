SRC := src/driver.cpp src/gui.cpp src/tree.cpp src/credentials.cpp src/options.cpp src/popup.cpp src/error.cpp
INC := ./headers/
NAME := classcatcher-gtk
CC := g++
FLAGS := -Wall -g
PKG := `pkg-config gtkmm-3.0 --cflags --libs`
DIR := /home/brian/Programs/classcatcher

main:
	${CC} ${FLAGS} ${SRC} -I${INC} -o ${NAME} ${PKG}
	mkdir -p ~/.local
	mkdir -p ~/.local/share
	mkdir -p ~/.local/share/applications
	echo "[Desktop Entry]\nName=Class Catcher\nComment=Class Catcher\nExec="${DIR}"/classcatcher-client -s\nIcon=preferences-system\nTerminal=false\nType=Application\nCategories=Application;Development;Programming;\nStartupNotify=true" > ~/.local/share/applications/classcatcher.desktop
	echo "background yes\nuse_xft yes\nown_window yes\nown_window_type normal\n#own_window_transparent no\nown_window_argb_visual yes\nown_window_argb_value 0\nown_window_hints undecorated,below,sticky,skip_taskbar,skip_pager\ndouble_buffer yes\nminimum_size 100 100\ndraw_shades no\ndraw_outline no\ndefault_color white\ndefault_shade_color black\nalignment top_right\noverride_utf8_locale yes\ntext_buffer_size 256\ngap_y 0\ngap_x 15\n\nTEXT\n$$""{font Arial:size=10:style=bold}$$""alignr $$""{exec /home/brian/Programs/classcatcher/include/display.sh}" > ${DIR}/etc/.conkyrc_classcatcher

clean:
	rm -f */*~ *~
