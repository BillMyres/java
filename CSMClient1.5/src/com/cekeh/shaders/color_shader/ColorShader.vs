// Last edit: 05/26/2018 - TvB
// Created:   05/20/2018 - TvB
#version 150

in vec3 position;

uniform float is3D;

uniform mat4 transformation_matrix;
uniform mat4 view_matrix;

mat4 projectionMatrix();

uniform float aspect_ratio;
float fov = 75;
float znear = 0.5;
float zfar = 1000;

void main(void){
	gl_Position = vec4(position, 1.0);
	
	if(is3D > 0){
		gl_Position *= transformation_matrix;
		gl_Position *= view_matrix;
		gl_Position *= projectionMatrix();
	}
}

mat4 projectionMatrix(){
	float scale = (1.0 / tan(radians(fov) / 2.0));
	
	float yscale = scale * aspect_ratio;
	float xscale = scale;

	mat4 matrix;
	matrix[0][0] = xscale;
	matrix[1][1] = yscale;
	matrix[2][2] = -((zfar + znear) / (zfar - znear));
	matrix[2][3] = -1.0;
	matrix[3][2] = -((2.0 * znear * zfar) / (zfar - znear));
	matrix[3][3] = 0.0;
	
	return matrix;
}