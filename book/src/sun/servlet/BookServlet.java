package sun.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import sun.bean.Book;
import sun.service.BookService;
import sun.service.impl.BookServiceImpl;
import sun.utils.BaseServlet;
import sun.utils.Page;
import sun.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "BookServlet", value = "/BookServlet")
public class BookServlet extends BaseServlet {

    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"),Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"),0);
        int max = WebUtils.parseInt(request.getParameter("max"),10000);
        BookService bookService = new BookServiceImpl();
        Page page = bookService.pageByPrice(pageNo, pageSize , new BigDecimal(min), new BigDecimal(max));
        page.setUrl("BookServlet?action=pageByPrice&min="+min+"&max="+max+"&");
        System.out.println(page);
        request.setAttribute("page",page);
        //response.sendRedirect("home.jsp");
        request.getRequestDispatcher("/home.jsp").forward(request,response);
    }

    protected void searchPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        String name = request.getParameter("name") == null ? "" : request.getParameter("name") ;
        String author = request.getParameter("author") == null ? "" : request.getParameter("author") ;
        Integer min = WebUtils.parseInt(request.getParameter("min"),0);
        Integer max = WebUtils.parseInt(request.getParameter("max"),0);
        request.setAttribute("name",name);
        request.setAttribute("author",author);
        request.setAttribute("min",request.getParameter("min"));
        request.setAttribute("max",request.getParameter("max"));
        //1 ????????????????????? pageNo ??? pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);//
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        try {
            Page<Book> page = bookService.page(pageNo,pageSize,name,author,new BigDecimal(min),new BigDecimal(max));
            page.setUrl("BookServlet?action=searchPage&name="+name+"&author="+author+"&min="+(min==0?"":min)+"&max="+(max==0?"":max)+"&");
            request.setAttribute("page",page);
            request.getRequestDispatcher("/home.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        BookService bookService = new BookServiceImpl();
        try {
            bookService.deleteBookById(Integer.valueOf(id));
            System.out.println("???????????????");
            response.sendRedirect("BookServlet?action=page&pageNo="+request.getParameter("pageNo"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNo = request.getParameter("pageNo");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        Book book = new Book();
        book.setId(Integer.valueOf(id));
        File fileDelete = new File("D:/" + request.getParameter("imgPath"));
        BookService bookService = new BookServiceImpl();
        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                List<FileItem> list = servletFileUpload.parseRequest(request);
                for (FileItem fileItem : list) {
                    if (fileItem.isFormField()) {
                        if("id".equals(fileItem.getFieldName())){
                            book.setId(Integer.parseInt(fileItem.getString()));
                        } else if ("name".equals(fileItem.getFieldName())) {
                            book.setName(fileItem.getString("utf-8"));//?????????
                        } else if ("author".equals(fileItem.getFieldName())) {
                            book.setAuthor(fileItem.getString("utf-8"));//????????????
                        } else if ("price".equals(fileItem.getFieldName())) {
                            book.setPrice(new BigDecimal(fileItem.getString()));//????????????
                        } else if ("sales".equals(fileItem.getFieldName())) {
                            book.setSales(Integer.valueOf(fileItem.getString()));//????????????
                        } else if ("stock".equals(fileItem.getFieldName())) {
                            book.setStock(Integer.parseInt(fileItem.getString()));//????????????
                        }
//                        }else if("oldPath".equals(fileItem.getFieldName())){
//                            book.setImgPath(fileItem.getString());
//                        }
                    } else {
                        String filename = fileItem.getName();
                    if(!filename.equals("")) {
                        fileDelete.delete();
                        String suffix = filename.substring(filename.lastIndexOf("."));
                        String newfilename = System.currentTimeMillis() + suffix;
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String format = simpleDateFormat.format(new Date());
                        File file = new File("d:/bookimg/" + format + "/");
                        if(!file.exists()){//??????????????????????????????????????????
                            file.mkdirs();//????????????
                        }
                        System.out.println(file.getAbsolutePath());
                        fileItem.write(new File(file, newfilename));
                        book.setImgPath("/bookimg/" + format + "/" + newfilename);
                    }
                    }
                }
                bookService.updateBook(book);
                //request.getRequestDispatcher("/BookServlet?action=page").forward(request, response);
                response.sendRedirect("BookServlet?action=page&pageNo="+ pageNo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            out.println("??????????????????..??????????????????!");
        }
    }

    protected void queryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        BookService bookService = new BookServiceImpl();
        String pageNo = request.getParameter("pageNo");
        try {
            Book book = bookService.queryBookById(Integer.valueOf(id));
            System.out.println(book.getImgPath());
            request.setAttribute("book",book);
            request.setAttribute("pageNo",pageNo);
            request.getRequestDispatcher("pages/manager/book_edit.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //????????????
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PrintWriter out = response.getWriter();
            Book book = new Book();
            BookService bookService = new BookServiceImpl();
            System.out.println("???????????????");
            if(ServletFileUpload.isMultipartContent(request)){
                //??????FileItemFactory ???????????????
                FileItemFactory fileItemFactory = new DiskFileItemFactory();
                // ??????????????????????????????????????????ServletFileUpload ???
                ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
                try {
                    // ????????????????????????????????????????????????FileItem
                    List<FileItem> list = servletFileUpload.parseRequest(request);
                    //?????????6????????????????????????
                    for (FileItem fileItem : list) {
                        //??????????????????????????????,???????????????????????????
                        if(fileItem.isFormField()){
                            //?????????????????????
                            //System.out.println(fileItem.getFieldName() +" = " + MyUtils.parseString(fileItem.getString()));
                            if("name".equals(fileItem.getFieldName())){
                                book.setName(fileItem.getString("utf-8"));//?????????
                            }else if("author".equals(fileItem.getFieldName())){
                                book.setAuthor(fileItem.getString("utf-8"));//????????????
                            }else if("price".equals(fileItem.getFieldName())){
                                book.setPrice(new BigDecimal(fileItem.getString()));//????????????
                            }else if("sales".equals(fileItem.getFieldName())){
                                book.setSales(Integer.valueOf(fileItem.getString()));//????????????
                            }else if("stock".equals(fileItem.getFieldName())){
                                book.setStock(Integer.parseInt(fileItem.getString()));//????????????
                            }
                        }else{
                            //??????????????????(????????????)
                            String filename = fileItem.getName();//???????????????
                            //????????? = 123.jpg       suffix = .jpg
                            String suffix = filename.substring(filename.lastIndexOf("."));
                            //?????????????????? + ?????? = ????????????
                            String newfilename =  System.currentTimeMillis() + suffix;

                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            String format = simpleDateFormat.format(new Date());
                            File file = new File("d:/bookimg/"+format+"/");
                            if(!file.exists()){//??????????????????????????????????????????
                                file.mkdirs();//????????????
                            }
                            System.out.println(file.getAbsolutePath());
                            fileItem.write(new File(file,newfilename));//????????????
                            book.setImgPath("/bookimg/"+format+"/"+newfilename);//????????????
                        }
                    }
                    bookService.addBook(book);//?????????????????????????????????
                    response.sendRedirect("BookServlet?action=page");
                } catch (FileUploadException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                out.println("??????????????????..??????????????????!");
            }
        }

    //??????????????????
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        //1 ????????????????????? pageNo ??? pageSize
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);//
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

        try {
            Page<Book> page = bookService.page(pageNo,pageSize);
            page.setUrl("BookServlet?action=page&");
            request.setAttribute("page",page);
            request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
