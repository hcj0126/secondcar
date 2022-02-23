package com.hcj.web;

import com.hcj.constant.BaseConstants;
import com.hcj.domain.Collection;
import com.hcj.domain.*;
import com.hcj.dto.DataBase;
import com.hcj.service.*;
import com.hcj.util.DateUtil;

import java.util.*;

/**
 * CarController
 *
 * @author hcj
 * @date 2022-02-16
 */
public class CarSystemController {

    private Scanner sc;

    private UserService userService;

    // 用户登录
    private User loginUser;

    private DataBase dataBase;

    private SeriesService seriesService;

    private BrandService brandService;

    private CarService carService;

    // 创建加入对比车辆的集合
    private List<Car> list = null;

    private CollectionService collectionService;

    public CarSystemController() {
        init();
    }

    /**
     * 初始化对象
     *
     * @return void
     */
    public void init() {
        sc = new Scanner(System.in);
        userService = new UserService();
        dataBase = new DataBase();
        seriesService = new SeriesService();
        brandService = new BrandService();
        carService = new CarService();
        collectionService = new CollectionService();
    }

    /**
     * 启动系统平台
     */
    public void startSystem() {
        System.out.println("---------欢迎来到二手车交易系统登录注册页-----------");
        System.out.println("1.登陆");
        System.out.println("2.注册");
        System.out.println("3.退出系统");
        System.out.println("请你选择：");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                // 1.登陆
                login();
                break;
            case 2:
                // 2.注册
                regist();
                break;
            case 3:
                // 3.退出系统
                System.out.println("欢迎下次光临！");
                System.exit(0);
                break;
            default:
                System.out.println("对不起，你的输入有误，请输入1-3的数字！");
                startSystem();
        }
    }

    /**
     * 1.登陆
     */
    public void login() {
        System.out.println("请输入用户名：");
        String userName = sc.next();
        System.out.println("请输入密码：");
        String password = sc.next();
        // 查找数据库里的用户名是否已存在
        User user = userService.findUserByName(userName);
        if (user != null) {
            // 判断密码是否正确
            if (user.getPassword().equals(password)) {
                loginUser = user;
                System.out.println("登录成功,1秒后进入主页面。。。。。。");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 登录后进入最新二手车信息菜单
                mainMenu();
            } else {
                System.out.println("密码输入错误，请按0重新输入 其他任意键返回上一层菜单");
            }
        } else {
            System.out.println("用户名输入错误，请按0重新输入 其他任意键返回上一层菜单");
        }
    }

    /**
     * 2.新用户注册
     */
    public void regist() {
        System.out.println("请输入注册的用户名：");
        String userName = sc.next();
        // 查找数据库里的用户名是否已存在 要求用户名唯一
        User user = userService.findUserByName(userName);
        // 说明用户名未被注册
        if (user == null) {
            System.out.println("请输入注册的密码：");
            String password = sc.next();
            // 创建一个用户
            User registUser = new User(UUID.randomUUID().toString(), userName, password);
            // 存入到数据库中
            userService.addUser(registUser);
            // 初始化数据库中记录该用户的收藏车辆
            //dataBase.getCollectCarData().put(registUser.getUserId(), new LinkedList<>());
            // 最后到启动系统平台
            System.out.println("用户名注册成功！.......2秒后跳转到系统平台页面");
            try {
                // 定时器 单位是毫秒 1000毫秒=1秒
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 2秒后跳转到系统平台登录页面
            startSystem();
        } else {
            System.out.println("该用户名已被占用！请按0重新输入 其他任意键返回上一层菜单");
        }
    }

    /**
     * 主菜单
     */
    public void mainMenu() {
        System.out.println("---------欢迎来到二手车交易系统主页面-----------");
        System.out.println("1.最新二手车信息");
        System.out.println("2.搜索车辆");
        System.out.println("3.对比车辆");
        System.out.println("4.我的收藏");
        System.out.println("5.后台管理");
        System.out.println("6.退出系统");
        System.out.println("请选择：");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                // 1.最新二手车信息
                newCarInfo();
                break;
            case 2:
                // 2.搜索车辆
                searchCar();
                break;
            case 3:
                // 3.对比车辆
                compareCarInfo();
                break;
            case 4:
                // 4.我的收藏
                myCollectCar();
                break;
            case 5:
                // 5.后台管理

                break;
            case 6:
                // 6.退出系统
                System.out.println("欢迎下次光临！");
                System.exit(0);
                break;
            default:
                System.out.println("对不起，你的输入有误，请输入1-6的数字！");
                mainMenu();
        }
    }

    /**
     * 1.实现最新二手车信息的方法
     */
    public void newCarInfo() {
        //查询最新二手车
        List<Car> newCarList = carService.findNewPublishCarList();
        //呈现车辆的基本信息
        printBaseCarInfo(newCarList);
        System.out.println("请选择序号：");
        int choiceDuibi = sc.nextInt();
        Car car = newCarList.get(choiceDuibi - 1);
        //调用呈现一个车辆的详细信息的方法
        carInfo(car);
    }

    /**
     * 2. 搜索车辆
     */
    public void searchCar() {
        System.out.println("1.根据品牌搜索");
        System.out.println("2.根据价格搜索");
        System.out.println("3.根据上牌年份搜索");
        System.out.println("4.根据里程数搜索");
        System.out.println("请选择：");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                // 1.根据品牌搜索
                brandSearch();
                break;
            case 2:
                // 2.根据价格搜索
                priceSearch();
                break;
            case 3:
                // 3.根据上牌年份搜索
                passDateSearch();
                break;
            case 4:
                // 4.根据里程数搜索
                passMileageSearch();
                break;
            default:
                System.out.println("对不起，你的输入有误，请输入1-4的数字！");
                searchCar();
        }
    }

    /**
     * 2.1 根据品牌搜索
     */
    public void brandSearch() {
        // 查找品牌
        List<Brand> brandList = brandService.findAllBrands();
        // 遍历所有的品牌集合
        for (int i = 0; i < brandList.size(); i++) {
            System.out.println((i + 1) + "." + brandList.get(i).getBrandName());
        }
        System.out.println("请选择品牌：");
        int choiceBrand = sc.nextInt();
        // 获取品牌对象
        Brand brand = brandList.get(choiceBrand - 1);
        // 根据品牌查找车型
        List<Series> seriesList = seriesService.findSeriesListByBrandId(brand.getBrandId());
        // 遍历所有的车型
        for (int i = 0; i < seriesList.size(); i++) {
            System.out.println((i + 1) + "." + seriesList.get(i).getSeriesName());
        }
        System.out.println("请选择车型：");
        int choiceSeries = sc.nextInt();
        // 获取具体车型的详细信息
        Series series = seriesList.get(choiceSeries - 1);
        // 根据车型查找车辆的详细信息
        List<Car> carList = carService.findCarListBySeriesId(series.getSeriesId());
        // 打印车型所有集合的详细信息
        printBaseCarInfo(carList);
        System.out.println("请选择：");
        int choiceCompare = sc.nextInt();
        Car car = carList.get(choiceCompare - 1);
        // 调用呈现一个车辆的详细信息的方法
        carInfo(car);
    }

    /**
     * 2.2 根据价格搜索
     */
    public void priceSearch() {
        // 输入一个搜索价格范围
        System.out.println("请输入你心里预期的最低价格：");
        double minPrice = sc.nextDouble();
        System.out.println("请输入你心里预期的最高价格：");
        double maxPrice = sc.nextDouble();
        // 根据价格范围查找车辆列表
        List<Car> carList = carService.findCarListByPrice(minPrice, maxPrice);
        // 打印车辆集合的详细信息
        printBaseCarInfo(carList);
        System.out.println("请选择：");
        int choiceCompare = sc.nextInt();
        Car car = carList.get(choiceCompare - 1);
        // 调用呈现一个车辆的详细信息的方法
        carInfo(car);
    }

    /**
     * 2.3 根据上牌年份搜索
     */
    public void passDateSearch() {
        System.out.println("请输入上牌的年份(格式yyyy-mm-dd)：");
        String year = sc.next();
        List<Car> list = carService.findCarListByYear(year);
        // 打印车辆集合的详细信息
        printBaseCarInfo(list);
        System.out.println("请选择：");
        int choiceCompare = sc.nextInt();
        Car car = list.get(choiceCompare - 1);
        // 调用呈现一个车辆的详细信息的方法
        carInfo(car);
    }

    /**
     * 2.4 根据里程数搜索
     */
    public void passMileageSearch() {
        System.out.println("请输入里程数：");
        int mileage = sc.nextInt();
        List<Car> list = carService.findCarListByMileage(mileage);
        // 打印车辆集合的详细信息
        printBaseCarInfo(list);
        System.out.println("请选择：");
        int choiceCompare = sc.nextInt();
        Car car = list.get(choiceCompare - 1);
        // 调用呈现一个车辆的详细信息的方法
        carInfo(car);
    }

    /**
     * 3. 对比列表里的车辆详细信息
     */
    public void compareCarInfo() {
        // 说明是登录状态
        if (loginUser != null) {
            // 获取用户的对比车辆数据
            List<Car> list = loginUser.getCompareCarList();
            if (list.size() <= 1) {
                System.out.println("请先去添加2辆以上的对比车辆");
                return;
            }
            // 创建集合，用于封装所有车辆的对比信息
            List<CompareInfo> compareList = new ArrayList<CompareInfo>();
            // 遍历对比车辆
            for (int i = 1; i <= 4; i++) {
                CompareInfo info = new CompareInfo();
                if (i == 1) {
                    info.getTitle().add("xxxx品牌：");
                    for (Car car : list) {
                        // 查找车型
                        Series series = seriesService.findSeriesById(car.getSeriesId());
                        // 查找品牌
                        Brand brand = brandService.findBrandById(series.getBrandId());
                        // 封装品牌信息--品牌名称
                        info.getTitle().add(brand.getBrandName());
                    }
                } else if (i == 2) {
                    info.getTitle().add("xxxx车型：");
                    for (Car car : list) {
                        // 查找车型
                        Series series = seriesService.findSeriesById(car.getSeriesId());
                        // 封装品牌信息--车型名称
                        info.getTitle().add(series.getSeriesName());
                    }
                } else if (i == 3) {
                    info.getTitle().add("xxxx价格：");
                    for (Car car : list) {
                        // 封装品牌信息--价格
                        info.getTitle().add(car.getPrice());
                    }
                } else if (i == 4) {
                    info.getTitle().add("离合器类型：");
                    for (Car car : list) {
                        String driveTypeName = changDriveTypeToString(car.getDriveType());
                        // 封装品牌信息
                        info.getTitle().add(driveTypeName);
                    }
                }
                // 将每个对比对象添加到集合中
                compareList.add(info);
            }
            // 遍历对比车辆的集合
            for (CompareInfo info : compareList) {
                for (Object obj : info.getTitle()) {
                    System.out.print(obj + "\t\t");
                }
                System.out.println();
            }
        } else {
            System.out.println("您还未登录！请先去登录");
            startSystem();
        }
    }

    /**
     * 4. 我的收藏
     */
    public void myCollectCar() {
        if (loginUser != null) {
            // 获取当前登录用户的收藏列表
            List<Collection> list = collectionService.findMyCollectionListByUserId(loginUser.getUserId());
            // 打印收藏列表的所有车辆信息
            printMyCollectionCarInfo(list);
            System.out.println("5秒后跳到主页面。。。。。。");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainMenu();
        } else {
            System.out.println("您还未登录！请先去登录");
            startSystem();
        }
    }

    /**
     * 呈现车辆的详细信息
     */
    public void carInfo(Car car) {
        printCarInfo(car);
        System.out.println("1.收藏（必须登陆后才能进行收藏）");
        System.out.println("2.加入对比");
        System.out.println("3.购买（只能在登陆后才能看到该菜单项）");
        System.out.println("4.返回主菜单");
        System.out.println("请选择：");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                // 1.加入收藏（必须登陆后才能进行收藏）
                joinCollectCar(car);
                break;
            case 2:
                // 2.加入对比
                joinCompareCar(car);
                while (true) {
                    againJoinCompareCar(car);
                    if (list.size() == 4) {
                        System.out.println("不好意思，对比车辆已满！");
                        break;
                    }
                }
                // 打印对比车辆信息
                printCompareCar();
                break;
            case 3:
                // 3.购买车辆（只能在登陆后才能看到该菜单项）
                buyCar(car);
                break;
            case 4:
                // 4.返回主菜单
                mainMenu();
                break;
            default:
                System.out.println("对不起，你的输入有误，请输入1-4的数字！");
                carInfo(car);
        }
    }

    /**
     * 1.加入收藏
     */
    public void joinCollectCar(Car car) {
        if (loginUser != null) {
            // 获取当前登录用户的收藏列表
            LinkedList<Collection> list = collectionService.findMyCollectionLinkByUserId(loginUser.getUserId());
            // 判断是否收藏了重复车辆
            if (list.contains(car)) {
                System.out.println("该车辆已收藏过！");
                carInfo(car);
            } else {
                // 未收藏过
                Collection collection = new Collection();
                collection.setCollectionId(UUID.randomUUID().toString());
                collection.setUserId(loginUser.getUserId());
                collection.setCarId(car.getCarId());
                collection.setCreateTime(new Date());
                if (list.size() >= 10) {
                    // 满10辆后移除第一辆车后再加入
                    list.removeFirst();
                    list.add(collection);
                } else {
                    list.add(collection);
                }
                // 添加到数据库Collection表中
                collectionService.insertCollection(collection);
                System.out.println("已收藏！");
                mainMenu();
            }
        } else {
            System.out.println("您还未登录！请先去登录");
            startSystem();
        }
    }

    /**
     * 2.加入对比车辆的列表
     */
    public void joinCompareCar(Car car) {
        // 让用户先登录后加入对比车辆
        if (loginUser != null) {//说明是登录状态
            list = loginUser.getCompareCarList();
            //判断对比车辆是否重复
            if (list.contains(car)) {
                System.out.println("不好意思，该车辆已经添加过对比车辆");
                carInfo(car);
            } else {
                //代表车辆未添加过
                //判断对比车辆是否超过4辆
                if (list.size() >= 4) {
                    System.out.println("不好意思，对比车辆已满");
                    carInfo(car);
                } else {
                    //加入对比车辆
                    list.add(car);
                    System.out.println("添加对比车辆成功！");
                }
            }
        } else {
            System.out.println("您还未登录！请先去登录");
            startSystem();
        }
    }

    /**
     * 2.继续加入对比车辆
     */
    public void againJoinCompareCar(Car car) {
        System.out.println("是否继续添加对比车辆,输入y(y:继续,任意键返回主菜单)");
        String y = sc.next();
        if (y.equalsIgnoreCase("y")) {
            //查询最新二手车
            List<Car> newCarList = carService.findNewPublishCarList();
            //呈现车辆的基本信息
            printBaseCarInfo(newCarList);
            System.out.println("请选择：");
            int choice = sc.nextInt();
            joinCompareCar(newCarList.get(choice - 1));
        } else {
            //返回主菜单
            mainMenu();
        }
    }

    /**
     * 3.购买车辆（只能在登陆后才能看到该菜单项）
     */
    public void buyCar(Car car) {
        if (loginUser != null) {
            // 根据车辆查找车型
            Series series = seriesService.findSeriesById(car.getSeriesId());
            // 根据车型查找品牌
            Brand brand = brandService.findBrandById(series.getBrandId());
            System.out.println("你确定要购买" + brand.getBrandName() + "这辆车吗？");
            System.out.println("请输入你的选择，y/n(y代表确定，任意键代表返回)：");
            String yes = sc.next();
            if (yes.equalsIgnoreCase("y")) {
                // 比较自己的钱要比车的钱多
                if (loginUser.getBalance() >= car.getPrice()) {
                    // 说明钱够
                    double carPrice = car.getPrice();//二手车的价格
                    double myPrice = loginUser.getBalance();
                    myPrice = myPrice - carPrice;
                    // 付款后再存入余额中
                    loginUser.setBalance(myPrice);
                    System.out.println("支付成功！支付了￥" + carPrice + "账户余额:￥" + loginUser.getBalance());
                    // 车卖出后，要下架
                    car.setFlag(BaseConstants.CAR_FLAG_NO);
                    // 更新数据库中车辆信息和用户信息
                    carService.updateCar(car);
                    userService.updateUser(loginUser);
                    // 返回菜单
                    mainMenu();
                } else {
                    System.out.println("你太穷了，好好去搬砖吧！");
                }
            } else {
                // 返回菜单
                mainMenu();
            }
        } else {
            System.out.println("您还未登录！请先去登录");
            startSystem();
        }
    }

    /**
     * 打印车辆集合的详细信息
     */
    public void printBaseCarInfo(List<Car> newCarList) {
        if (newCarList.isEmpty()) {
            System.out.println("没有相关车辆信息");
        }
        // 品牌   车型   离合器类型  价格   发布时间
        System.out.println("序号\t品牌\t车型\t\t离合器类型\t\t价格\t\t上牌时间\t\t里程数");
        for (int i = 0; i < newCarList.size(); i++) {
            Car car = newCarList.get(i);
            if (car != null) {
                // 根据车辆查找车型
                Series series = seriesService.findSeriesById(car.getSeriesId());
                // 根据车型查找品牌
                Brand brand = brandService.findBrandById(series.getBrandId());
                String driveTypeName = changDriveTypeToString(car.getDriveType());
                System.out.println((i + 1) + ".\t" + brand.getBrandName() + "\t" + series.getSeriesName() + "\t" +
                        driveTypeName + "\t\t" + car.getPrice() + "\t" +
                        DateUtil.dateFormat(car.getPassDate(), "yyyy-MM-dd") + "\t" + car.getMileage() + "公里"
                );
            }

        }
    }

    /**
     * 打印我的收藏车辆集合的详细信息
     */
    public void printMyCollectionCarInfo(List<Collection> myCollectionList) {
        if (myCollectionList.isEmpty()) {
            System.out.println("没有相关车辆信息");
        }
        // 品牌   车型   离合器类型  价格   发布时间
        System.out.println("序号\t品牌\t车型\t\t离合器类型\t\t价格\t\t上牌时间");
        for (int i = 0; i < myCollectionList.size(); i++) {
            String carId = myCollectionList.get(i).getCarId();
            Car car = carService.findCarByCarId(carId);
            if (car != null) {
                // 根据车辆查找车型
                Series series = seriesService.findSeriesById(car.getSeriesId());
                // 根据车型查找品牌
                Brand brand = brandService.findBrandById(series.getBrandId());
                String driveTypeName = changDriveTypeToString(car.getDriveType());
                System.out.println((i + 1) + ".\t" + brand.getBrandName() + "\t" + series.getSeriesName() + "\t" +
                        driveTypeName + "\t\t" + car.getPrice() + "\t" + DateUtil.dateFormat(car.getPassDate(), "yyyy-MM-dd"));
            }

        }
    }

    /**
     * 打印一辆车辆的详细信息
     */
    public void printCarInfo(Car car) {
        // 品牌   车型   离合器类型  价格   发布时间
        System.out.println("品牌\t车型\t离合器类型\t价格\t\t上牌时间");
        // 根据车辆查找车型
        Series series = seriesService.findSeriesById(car.getSeriesId());
        // 根据车型查找品牌
        Brand brand = brandService.findBrandById(series.getBrandId());
        String driveTypeName = changDriveTypeToString(car.getDriveType());
        System.out.println(brand.getBrandName() + "\t" + series.getSeriesName() + "\t" +
                driveTypeName + "\t\t" + car.getPrice() + "\t" + DateUtil.dateFormat(car.getPassDate(), "yyyy-MM-dd"));
    }

    /**
     * 离合器类型0 1 2 数字转换成相应自动档，手动档...
     */
    public String changDriveTypeToString(int driveType) {
        String driveTypeName = null;
        // 0手动档  1自动档   2手自一体
        if (driveType == 0) {
            driveTypeName = "手动档";
        } else if (driveType == 1) {
            driveTypeName = "自动档";
        } else if (driveType == 2) {
            driveTypeName = "手自一体";
        }
        return driveTypeName;
    }

    /**
     * 打印对比车辆的方法，紧跟着joinCompareCar()
     */
    public void printCompareCar() {
        // 加入对比可以去查看对比车辆的详细信息
        System.out.println("该车辆已加入对比列表，按0查看对比车辆，按其他键返回主菜单");
        System.out.println("请选择：");
        String choice = sc.next();
        if (choice.equals("0")) {
            // 查看对比列表里的车辆信息
            compareCarInfo();
        } else {
            mainMenu();
        }
    }
}
