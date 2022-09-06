#include <iostream>
#include <opencv2/opencv.hpp>
#include <random>
using namespace cv;
using namespace std;
int main(int argc, char *argv[]) {
    int spatialRad = 64;
    int colorRad = 32;
    int maxPryLevel = 2;
    int radius;
    if (argc != 2) {
        return -2;
    }
    auto image = imread(argv[1]);
    if (image.empty()) {1
        return -1;
    }

    while (cout << "Please input spatial radius, color radius and max pyramid level: "
    && cin >> spatialRad >> colorRad >> maxPryLevel) {
        Mat result;
        pyrMeanShiftFiltering(
                image,
                result,
                spatialRad,
                colorRad,
                maxPryLevel
        );
        imshow("result", result);
        imwrite("mean_shift.jpg", result);
        waitKey();
        destroyAllWindows();

        while (cout << "Please input the radius to fill: " && cin >> radius) {
            if (radius < 0) {
                break;
            }
            auto segmentation = result.clone();
            default_random_engine rng;
            uniform_int_distribution<> U(0,255);
            Mat mask(
                    segmentation.rows + 2,
                    segmentation.cols + 2,
                    CV_8U,
                    Scalar::all(0)
            );
            for (auto i = 0; i < segmentation.rows; i++) {
                for (auto j = 0; j < segmentation.cols; j++) {
                    if (mask.at<uchar>(i + 1, j + 1) == 0) {
                        floodFill(
                                segmentation,
                                mask,
                                Point2i(j, i),
                                Scalar(U(rng), U(rng), U(rng)),
                                0,
                                Scalar::all(radius),
                                Scalar::all(radius)
                        );
                    }
                }
            }
            imshow("segmentation", segmentation);
            imwrite("flood_fill.jpg", segmentation);
            waitKey();
            destroyAllWindows();
        }
    }


    return 0;
}
