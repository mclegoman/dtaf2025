#version 150

in vec2 texCoord;
in vec2 oneTexel;

out vec4 fragColor;

uniform sampler2D InSampler;
uniform sampler2D InDepthSampler;

void main() {
    vec4 inputColor = texture(InSampler, texCoord);
    float depth = texture(InDepthSampler, texCoord).r;
    fragColor = depth == 1.0 ? vec4(mix(inputColor.rgb, vec3(0.039, 0.043, 0.078), 0.128), inputColor.a) : inputColor;
}