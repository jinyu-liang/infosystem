rem @echo off

set SDL_DIR=%1
set base_dir=%2
set OUTPUT_PATH=%3
for /r %SDL_DIR%  %%s in (*.sdl) do call :sdl2java %%s

@goto :EOF

:sdl2java
cd %1\..
%base_dir%\sdl2java.exe -gc -d%OUTPUT_PATH% %1

@goto :EOF

rem @echo on