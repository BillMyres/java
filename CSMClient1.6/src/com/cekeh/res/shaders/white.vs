// Last edit: 07/15/2018 - TvB
// Created:   07/15/2018 - TvB
#version 150

in vec3 position;

uniform mat4 transformation_matrix;
uniform mat4 view_matrix;
uniform mat4 projection_matrix;

void main(void){
	gl_Position = vec4(position, 1);
	gl_Position *= transformation_matrix;
	gl_Position *= view_matrix;
	gl_Position *= projection_matrix;
}