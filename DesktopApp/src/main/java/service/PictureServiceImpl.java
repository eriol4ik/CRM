package service;

import dao.PictureDAO;
import entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Service("pictureService")
public class PictureServiceImpl extends ServiceImpl<Picture, Long> implements PictureService{
    @Autowired
    @Qualifier("pictureDAO")
    private PictureDAO pictureDAO;

    private PictureServiceImpl() {}
}
