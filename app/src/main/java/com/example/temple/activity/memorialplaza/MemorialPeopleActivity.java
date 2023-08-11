package com.example.temple.activity.memorialplaza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.example.temple.R;
import com.example.temple.activity.base.BaseTitleActivity;
import com.example.temple.activity.shop.ImagePreViewActivity;

import butterknife.BindView;

public class MemorialPeopleActivity extends BaseTitleActivity implements View.OnClickListener {
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.tv_picture)
    TextView tv_picture;
    @BindView(R.id.tv_video)
    TextView tv_video;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.tv_jidian)
    TextView tv_jidian;
    @BindView(R.id.tv_liuyan)
    TextView tv_liuyan;

    String nameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_memorial_people;
    }

    @Override
    protected void initView() {
        baseTitleGone();
        BarUtils.setStatusBarLightMode(MemorialPeopleActivity.this, false);
        nameString = getIntent().getStringExtra("name");
        name.setText(nameString);

        if ("屈原".equals(nameString)) {
            head.setImageResource(R.mipmap.quyuan);
            time.setText("约公元前340年—公元前278年");
            content.setText(" 屈原（约公元前340年—公元前278年），屈氏，名平，字原，又自云名正则，字灵均，出生于楚国丹阳秭归（今湖北宜昌），战国时期楚国诗人、政治家。早年受楚怀王信任，任左徒、三闾大夫，兼管内政外交大事。提倡“美政”，主张对内举贤任能，修明法度，对外力主联齐抗秦。因遭贵族排挤诽谤，被先后流放至汉北和沅湘流域。楚国郢都被秦军攻破后，自沉于汨罗江。当听到他自尽的消息之后，人们纷纷划船来到汨罗江进行捞救。还不约而同地将饭团、鸡蛋等食物丢进江里，认为让江里的鱼虫虾蟹吃饱了，就不会咬屈的身体了，有的人将雄黄酒倒入江里，也是希望能够把蛟龙水兽药晕，以免伤害屈大夫。从此以后，每年农历五月初五端午节，民间就有了龙舟竞渡、吃粽子、饮雄黄酒的风俗，以此来纪念伟大的爱国诗人屈原。\n" +
                    "屈原是中国历史上一位伟大的爱国诗人，中国浪漫主义文学的奠基人，“楚辞”的创立者和代表作家，其主要作品有《离骚》《九歌》《九章》《天问》等。“路漫漫其修远兮，吾将上下而求索”，屈原的“求索”精神，成为后世仁人志士所信奉和追求的一种高尚精神。");
        } else if ("霍去病".equals(nameString)) {
            time.setText("公元前140年-前117年");
            content.setText("霍去病（公元前140年-前117年），河东郡平阳县（今山西省临汾市）人，西汉名将、军事家、民族英雄，汉武帝皇后卫子夫及大司马大将军卫青的外甥，大司马大将军霍光的同父异母兄长。\n" +
                    "霍去病十八岁为剽姚校尉，率领八百骑兵深入大漠，两次功冠全军，封冠军侯。元狩二年（前121年），二十岁的霍去病升任骠骑将军，指挥两次河西之战，俘匈奴祭天金人，直取祁连山，总计歼灭和招降河西匈奴近十万人，沉重地打击了匈奴右部。汉武帝分徙匈奴降众于边塞之外，因其故俗置五属国，又在其故地先后设立河西四郡，由此沟通了自内地与西域的直接交往，对西汉和匈奴势力的消长发生显著的影响。元狩四年（前119年），霍去病与卫青率军深入漠北，于漠北之战中消灭匈奴左贤王部主力七万余人，追击匈奴军直至狼居胥山与姑衍山，分祭天地，临翰海而还。 此战使“匈奴远遁，漠南无王庭” 。战后加拜大司马骠骑将军，与卫青同掌军政。元狩六年（前117年），霍去病病逝，年仅二十四岁。武帝赐谥号“景桓”，陪葬茂陵，并仿照祁连山的形状为其修筑坟墓。 \n" +
                    "   霍去病用兵灵活，注重方略，不拘古法，善于长途奔袭、快速突袭和大迂回、大穿插、歼灭战，为汉武帝时期的军事扩张做出重大贡献。他前后六次出击匈奴，与卫青等人合作，解除了匈奴对汉王朝的威胁。");
        } else if ("岳飞".equals(nameString)) {
            head.setImageResource(R.mipmap.yuefei);
            time.setText("1103年3月24日～1142年1月27日");
            content.setText("岳飞（1103年3月24日～1142年1月27日），男，字鹏举，相州汤阴（今河南省汤阴县）人。南宋时期抗金名将、军事家、战略家、民族英雄、书法家、诗人，位列南宋“中兴四将”之首。\n" +
                    "岳飞从二十岁起，曾先后四次从军。自建炎二年（1128年）遇宗泽至绍兴十一年（1141年）止，先后参与、指挥大小战斗数百次。金军攻打江南时，独树一帜，力主抗金，收复建康。绍兴四年（1134年），收复襄阳六郡。绍兴六年（1136年），率师北伐，顺利攻取商州、虢州等地。绍兴十年（1140年），完颜宗弼毁盟攻宋，岳飞挥师北伐，两河人民奔走相告，各地义军纷纷响应，夹击金军。岳家军先后收复郑州、洛阳等地，在郾城、颍昌大败金军，进军朱仙镇。宋高宗赵构和宰相秦桧却一意求和，以十二道“金字牌”催令班师。在宋金议和过程中，岳飞遭受秦桧、张俊等人诬陷入狱。1142年1月，以莫须有的罪名，与长子岳云、部将张宪一同遇害。宋孝宗时，平反昭雪，改葬于西湖畔栖霞岭，追谥武穆，后又追谥忠武，封鄂王。\n" +
                    "岳飞是南宋杰出的统帅，他重视人民抗金力量，缔造了“连结河朔”之谋，主张黄河以北的民间抗金义军和宋军互相配合，以收复失地；治军赏罚分明，纪律严整，又能体恤部属，以身作则，率领的“岳家军”号称“冻死不拆屋，饿死不掳掠 ”。金军有“撼山易，撼岳家军难”的评语，以示对岳家军的由衷敬佩。岳飞的文才同样卓越，其代表词作《满江红·怒发冲冠》 是千古传诵的爱国名篇，后人辑有文集传世。");
        } else if ("文天祥".equals(nameString)) {
            time.setText("1236年6月6日－1283年1月9日");
            content.setText(" 文天祥（1236年6月6日－1283年1月9日），初名云孙，字宋瑞，又字履善。自号浮休道人、文山。江南西路吉州庐陵县（今江西省吉安市青原区富田镇 ）人，南宋末年政治家、文学家，民族英雄，与陆秀夫、张世杰并称为“宋末三杰”。\n" +
                    "宋理宗宝祐四年（1256年），二十一岁的文天祥中进士第一，成为状元。一度掌理军器监兼权直学士院，因直言斥责宦官董宋臣，讥讽权相贾似道而遭到贬斥，数度沉浮，在三十七岁时自请致仕。德祐元年（1275年），元军南下攻宋，文天祥散尽家财，招募士卒勤王，被任命为浙西、江东制置使兼知平江府。在援救常州时，因内部失和而退守余杭。随后升任右丞相兼枢密使，奉命与元军议和，因面斥元主帅伯颜被拘留，于押解北上途中逃归。不久后在福州参与拥立益王赵昰为帝，又自赴南剑州聚兵抗元。景炎二年（1277年）再攻江西，终因势孤力单败退广东。祥兴元年（1278年）卫王赵昺继位后，拜少保，封信国公。后在五坡岭被俘，押至元大都，被囚三年，屡经威逼利诱，仍誓死不屈。元至元十九年十二月（1283年1月），文天祥从容就义，终年四十七岁。明代时追赐谥号“忠烈” 。\n" +
                    "文天祥多有忠愤慷慨之文，其诗风至德祐年间后一变，气势豪放，允称诗史。他在《过零丁洋》中所作的“人生自古谁无死，留取丹心照汗青”，气势磅礴，情调高亢，激励了后世众多为理想而奋斗的仁人志士，文天祥的著作经后人整理，被辑为《文山先生全集》。");
        } else if ("戚继光".equals(nameString)) {
            head.setImageResource(R.mipmap.qijigaung);
            time.setText("1528年11月12日－1588年1月5日");
            content.setText("戚继光（1528年11月12日－1588年1月5日），字元敬，号南塘、孟诸，山东蓬莱人，生于山东济宁 ，祖籍山东东平（一说祖籍安徽定远）。明朝抗倭名将，民族英雄，杰出的军事家、书法家、诗人。\n" +
                    "戚继光爱好读书，世袭登州卫指挥佥事，联合俞大猷等抗击倭寇十余年，扫平为祸多年的倭患，确保了沿海人民的生命财产安全；镇守北方，抗击蒙古部族内犯，保障了北部疆域的安全，促进了蒙汉民族的和平发展。凭借战功，累迁左都督、少保兼太子太保。万历十年（1582年），内阁首辅张居正病逝后，调往广东。万历十三年（1585年），戚继光受到弹劾，罢免回乡。万历十六年，病死于家中，时年六十一，谥号武毅。\n" +
                    "著有兵书《纪效新书》十八卷、《练兵实纪》十四卷本等著名兵书、《止止堂集》。同时，戚继光是一位杰出的兵器专家和军事工程家，改造、发明了各种火攻武器；建造的大小战船、战车，使明军水陆装备优于敌人；富有创造性地在长城上修建空心敌台，进可攻退可守，是极具特色的军事工程。");
        } else if ("王船山".equals(nameString)) {
            time.setText("1619-1692");
            content.setText("王夫之(1619-1692)，衡阳县人，字而农，号船山，亦号姜斋。生于衡州府王衙坪，青年流徙南岳、衡山、肇庆、常宁、耒阳、永州、郴州、桂阳、清远、桂林、邵阳等地，1648年举兵衡山，抗击清兵南下。后在南明朝廷翰林院、行人司等处任职。晚年隐居衡阳县石船山下32年。明末清初伟大的爱国主义思想家，被学界列入中国古代十大思想家、世界四大唯物主义哲学家，与黑格尔并称的东西方哲学双子星座。\n" +
                    "当今学界公认的中国优秀传统文化的集大成者，湖湘文化的精神源头之一与最高峰，被誉为“国学大学”。其著作涉及经史子集，一百余种，四百余卷，言逾千万，是中国贡献给人类的一座思想宝库。王夫之终生未曾剃发易服，得以完发以终，是一个孤高耿介的人，是中国知识分子中罕见其匹的具有高尚民族气节的人物。近年来，习近平总书记在重要公开场合至少六次提及王船山或船山名言。");
        } else if ("林则徐".equals(nameString)) {
            head.setImageResource(R.mipmap.linzexu);
            time.setText("1785年8月30日 [52] －1850年11月22日");
            content.setText("林则徐（1785年8月30日 [52] －1850年11月22日），字元抚，又字少穆、石麟，晚号俟村老人、俟村退叟、七十二峰退叟、瓶泉居士、栎社散人等，福建侯官县人，中国清代后期政治家、文学家、思想家，民族英雄。\n" +
                    "林则徐是嘉庆十六年（1811年）进士，历官翰林编修、浙江杭嘉湖道、江苏按察使、东河总督、江苏巡抚、湖广总督等职。在江苏整顿吏治、平反冤狱、兴修水利、救灾办赈；在湖广大力开展禁烟运动。道光十九年（1839年），以钦差大臣赴广东禁烟时，派人明察暗访，强迫外国鸦片商人交出鸦片，并将没收鸦片于虎门销毁。该事件被认为是第一次鸦片战争的导火线。战争爆发后，令广东军民严阵以待，使英军在粤无法得逞。不久后被构陷革职，遣戍伊犁。期间曾奉命赴浙江镇海协防，并留开封襄办黄河决口。道光二十五年（1845年）重获起用，历任陕甘总督、陕西巡抚、云贵总督等职，加太子太保。道光三十年（1850年），林则徐再任钦差大臣，奉命镇压拜上帝会起事，途中病逝于潮州普宁。获赠太子太傅，谥号“文忠”。有《林文忠公政书》等作品传世。 \n" +
                    "林则徐一生遍历地方，治绩卓著。虽在两广抗击西方入侵，但对于西方的文化、科技和贸易则持开放态度，主张学其优而用之。由他主持编译的《四洲志》及魏源编撰的《海国图志》，对晚清的洋务运动乃至日本的明治维新都具有启发作用。");
        } else if ("左宗棠".equals(nameString)) {
            head.setImageResource(R.mipmap.zuozongtang);
            time.setText("1812年-1885年");
            content.setText(" 左宗棠（1812年-1885年），字季高，一字朴存，号湘上农人， 湖南湘阴(今湖南省岳阳市湘阴县)人。中国近代民族英雄、政治家、军事家、诗人，洋务派代表人物之一。左宗棠在清道光十二年（1832年）中举人，后来屡试不第，但留意农事，遍读群书，钻研舆地、兵法，任湖南巡抚骆秉章幕僚，参赞戎机，抵抗太平军。后由曾国藩保举，特旨为四品京堂，率兵号“楚军”，与太平军作战，为扭转战局之关键人物，历官浙江巡抚、闽浙总督、陕甘总督、协办大学士，封恪靖伯。光绪元年（1875年）任钦差大臣督办新疆军务，讨伐阿古柏，收复失地。中、俄伊犁交涉中，主张“先之以谈判，继之以战阵”。光绪七年（1881年），任军机大臣，调两江总督，后病殁福州。\n" +
                    "     左宗棠是清末湘军首领之一，一生经历了湘军平定太平天国运动、洋务运动和收复新疆维护中国统一等重要历史事件，其还造就了一批优秀的中国近代工业技术人才和杰出的海军将士，在中国近代史上写下了浓墨重彩的一笔，为中国近代化的先驱者、近代中国国家主权完整的捍卫者、中国优秀传统文化的发展者、传承者， 与曾国藩、张之洞、李鸿章并成为“晚清中兴四大名臣”。 ");
        } else if ("孙中山".equals(nameString)) {
            time.setText("1866年11月12日-1925年3月12日");
            content.setText("孙中山（1866年11月12日-1925年3月12日），名文，字载之，号日新，又号逸仙，又名帝象，化名中山樵，伟大的民族英雄、伟大的爱国主义者、中国民主革命的伟大先驱 ，中华民国和中国国民党的缔造者，三民主义的倡导者，创立了《五权宪法》。他首举彻底反帝反封建的旗帜，“起共和而终两千年封建帝制”。\n" +
                    "孙中山1866年11月12日生于广东省广州府香山县（今中山市）翠亨村。孙中山原在香港学医，并成为西医医师。孙中山目睹中华民族有被西方列强瓜分的危险，决定抛弃“医人生涯”，进行“医国事业”。孙中山早期受郑观应的改良思想影响，后看清了清政府的腐败，决心推翻清王朝，建立民主共和国。1894年11月24日，孙中山在檀香山创立兴中会。1905年（光绪三十一年）成立中国同盟会。1911年10月10日（宣统三年）新军中的革命党人暗中联络，决定当天晚上起义。辛亥革命后被推举为中华民国临时大总统（任期1912年1月1日——1912年4月1日）。1925年3月12日，孙中山因癌症在北京逝世。1929年6月1日，根据其生前遗愿，葬于南京紫金山中山陵。1940年，国民政府通令全国，尊称其为“中华民国国父”。\n" +
                    "孙中山著有《建国方略》 《建国大纲》 《三民主义》等。其著述在逝世后多次被结集出版，有中华书局1986年出版的十一卷本《孙中山全集》，台北1969、1973、1985年出版的《国父全集》等。");
        } else if ("邱少云".equals(nameString)) {
            head.setImageResource(R.mipmap.qiushaoyun);
            time.setText("1926年7月12日 [12] -1952年10月12日");
            content.setText("邱少云（1926年7月12日 [12] -1952年10月12日） 男，汉族，四川省铜梁县（今重庆市铜梁区）人，中共党员。\n" +
                    "    1949年入伍，生前系中国人民志愿军第15军87团9连战士。1952年10月中旬，在抗美援朝一次战斗中，邱少云所在营奉命担负潜伏任务。潜伏前，邱少云向党支部递交了入党申请书，写道：“宁愿自己牺牲，决不暴露目标，为了整体，为了胜利，为了中朝人民和全人类的解放事业，愿献出自己的一切”。执行任务中，邱少云在距敌前沿阵地60多米的草丛中潜伏时，敌人突然向潜伏区逼近，为了掩护潜伏部队，指挥所命令炮兵对敌进行打击。敌人遭到打击后出动飞机侦察，并盲目发射侦察燃烧弹，一颗燃烧弹正好落在邱少云身边，飞迸的火星溅落在他的左腿上，烧着了他的棉衣、头发和皮肉。他身旁就是水沟，只要往水沟里一滚，就可以把火扑灭。但为了不暴露潜伏部队，他严守纪律，咬紧牙关，双手深深插进泥土中，以惊人的毅力忍受着剧痛，一声不吭、一动不动，直至壮烈牺牲，年仅26岁。上级党委追认他为中国共产党党员。他被中国人民志愿军总部授予“一级英雄”荣誉称号，并追记特等功一次。朝鲜民主主义人民共和国追授他英雄称号和金星奖章、一级国旗勋章。经中央军委批准，将其画像制作印发全军，在连以上单位悬挂、张贴。 2019年9月25日，入选“最美奋斗者”个人名单。");
        } else if ("董存瑞".equals(nameString)) {
            head.setImageResource(R.mipmap.dongcunrui);
            time.setText("1929年10月15日～1948年5月25日");
            content.setText("董存瑞（1929年10月15日～1948年5月25日） ，男， 汉族， 河北省张家口市怀来县人，中国共产党党员。 \n" +
                    "董存瑞出身于贫苦农民家庭。抗日战争时期，当过儿童团长，曾机智地掩护区委书记躲过侵华日军的追捕，被誉为“抗日小英雄”。1945年7月参加八路军，任某部6班班长。1947年3月加入中国共产党。他军事技术过硬，作战机智勇敢，在一次战斗中只身俘敌10余人。先后立大功3次、小功4次，获3枚“勇敢奖章”、1枚“毛泽东奖章”。\n" +
                    "1948年5月，中国人民解放军攻打隆化城的战斗打响。他所在连队担负攻击国民党守军防御重点隆化中学的任务。他任爆破组组长，带领战友接连炸毁4座炮楼、5座碉堡，顺利完成了规定任务。连队随即发起冲锋，但突然遭到敌人一隐蔽的桥型暗堡猛烈的火力封锁，部队接连两次对暗堡爆破均未成功。董存瑞挺身而出，向连长请战，连长批准了他的请求。他毅然抱起炸药包，冲向暗堡，前进中左腿负伤，顽强坚持冲至桥下。由于桥型暗堡距地面超过身高，两头桥台又无法放置炸药包。在部队攻击受阻的危急关头，他毫不犹豫地用左手托起炸药包，敌人碉堡被炸毁，董存瑞以自己的生命为部队开辟了前进道路，牺牲时年仅19岁。");
        } else if ("黄继光".equals(nameString)) {
            head.setImageResource(R.mipmap.huangjiguang);
            time.setText("黄继光");
            content.setText("黄继光（1931年1月8日～1952年10月20日），原名黄积广 ，四川省中江县人，中国人民志愿军第15军45师135团2营。\n" +
                    "1951年3月参加中国人民志愿军。1952年10月19日晚，黄继光所在的第2营奉命向上甘岭右翼597.9高地反击。在离天亮只有40多分钟时，黄继光用胸膛堵住疯狂扫射的敌人地堡机枪眼英勇牺牲 。中国人民志愿军给他追记特等功，追授“特级英雄”称号；所在部队追认他为中国共产党党员；朝鲜民主主义人民共和国追授他“朝鲜民主主义人民共和国英雄”称号和金星奖章和一级国旗勋章  。1962年10月，四川省中江县人民政府建立了“黄继光纪念馆”，朱德、董必武、刘伯承、郭沫若为之题词。1982年，邓小平为“黄继光纪念馆”的黄继光塑像基座题字：“特级英雄黄继光” 。2019年9月25日，中央宣传部、中央组织部和中央军委政治工作部等联合授予黄继光“最美奋斗者”荣誉称号 。");
        }


    }

    @BindView(R.id.iv_left)
    RelativeLayout iv_left;


    @Override
    protected void initListener() {
        super.initListener();
        iv_left.setOnClickListener(this);
        tv_picture.setOnClickListener(this);
        tv_video.setOnClickListener(this);
        tv_jidian.setOnClickListener(this);
        tv_liuyan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) {
            finish();
        } else if (v.getId() == R.id.tv_picture) {
//            String[] ids = new String[3];
//            ids[0] = "https://imgsa.baidu.com/baike/pic/item/d62a6059252dd42a987dad860a3b5bb5c9eab8a3.jpg";
//            ids[1] = "https://oss.gzdaily.cn/site2/pad/pic/2021-05/22/cae1f589-dafc-4d90-9b50-9fec3a5b6958.jpg";
//            ids[2] = "https://m.cnr.cn/news/20160129/W020160129065171491012.jpg";

            String[] ids = new String[1];
            ids[0] = nameString;

            Intent intent = new Intent(mContext, ImagePreViewActivity.class);
            intent.putExtra("imgIds", ids);
            intent.putExtra("curPosition", 0);
            startActivity(intent);
        } else if (v.getId() == R.id.tv_video) {
            Intent intent = new Intent(mContext, MemorialVideoActivity.class);
            intent.putExtra("name", nameString);
            if ("屈原".equals(nameString)) {
                intent.putExtra("video", "https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/qy.mp4");
            } else if ("霍去病".equals(nameString)) {
                intent.putExtra("video", "https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/hqb.mp4");
            } else if ("岳飞".equals(nameString)) {
                intent.putExtra("video", "https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/yf.mp4");
            } else if ("文天祥".equals(nameString)) {
                intent.putExtra("video", "https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/wtx.mp4");
            } else if ("戚继光".equals(nameString)) {
                intent.putExtra("video", "https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/qjg.mp4");
            } else if ("王船山".equals(nameString)) {
                intent.putExtra("video", "https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/wcs.mp4");
            } else if ("林则徐".equals(nameString)) {
                intent.putExtra("video", "https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/lzx.mp4");
            } else if ("左宗棠".equals(nameString)) {
                intent.putExtra("video", "https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/zzt.mp4");
            } else if ("孙中山".equals(nameString)) {
                intent.putExtra("video", "https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/szs.mp4");
            } else if ("邱少云".equals(nameString)) {
                intent.putExtra("video", "https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/qsy.mp4");
            } else if ("董存瑞".equals(nameString)) {
                intent.putExtra("video", "https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/dcr.mp4");
            } else if ("黄继光".equals(nameString)) {
                intent.putExtra("video", "https://cs-xyxj.oss-cn-hangzhou.aliyuncs.com/video/hjg.mp4");
            }
            startActivity(intent);
        }else if(v.getId() == R.id.tv_jidian) {
            startActivity(new Intent(MemorialPeopleActivity.this,MemorialCeremonyActivity.class));
        }else if(v.getId() == R.id.tv_liuyan){
            startActivity(new Intent(MemorialPeopleActivity.this,MemorialLiuYanActivity.class));
        }
    }


}