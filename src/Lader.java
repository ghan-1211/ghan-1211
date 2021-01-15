public class Lader {
    float above;
    float bottom;
    float height;
    float area;
    float computerArea() {
        area = (above+bottom)*height/2.0f;
        return area;
    }
    void setHeight(float h){  //定义setHeight方法
        height = h;
    }
}
