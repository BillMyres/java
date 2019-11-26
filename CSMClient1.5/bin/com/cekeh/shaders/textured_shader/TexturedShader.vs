// Last edit: 05/26/2018 - TvB
// Created:   05/20/2018 - TvB
#version 150

in vec3 position;
in vec2 texcoord;
in vec4 color;

out vec2 pass_texcoord;
out vec4 pass_color;

uniform float is3D;

uniform mat4 transformation_matrix;
uniform mat4 view_matrix;

mat4 projectionMatrix();

uniform float aspect_ratio;
float fov = 75;
float znear = 0.5;
float zfar = 1000;

void main(void){
	mat4 temp_matrix = transformation_matrix;
	
	int width = 25;
	int height = 25;
	
	float x = gl_InstanceID % width;
	float y = floor(float((gl_InstanceID % (width * height)) / height));
	float z = floor(float(gl_InstanceID / (width * height)));
	
	temp_matrix[0][3] += 10.0 * x;
	temp_matrix[1][3] -= 10.0 * y;
	temp_matrix[2][3] -= 10.0 * z;

	// Set the position of each vertex
	gl_Position = vec4(position, 1.0);
	
	if(is3D > 0){
		gl_Position *= temp_matrix;
		gl_Position *= view_matrix;
		gl_Position *= projectionMatrix();
	}
	
	// Set texture coordinates
	pass_texcoord = texcoord;
	
	// Set vertex color
	pass_color = color;
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