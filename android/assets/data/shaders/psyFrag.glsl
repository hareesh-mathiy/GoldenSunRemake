#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform float u_time;
uniform vec3 u_distort;

void main() {
	vec4 texColor = texture2D(u_texture, v_texCoords);
	vec3 white = texColor.rgb + u_distort;
	texColor.rgb = white;
	gl_FragColor = v_color * texColor;
}